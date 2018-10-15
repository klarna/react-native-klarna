#import "RNKlarnaView.h"
#import <React/RCTEventDispatcher.h>
#import <React/RCTUtils.h>
#import <React/RCTComponent.h>

@interface RNKlarnaView ()
@property (nonatomic, weak) RCTBridge *bridge;
@property (nonatomic, copy) RCTBubblingEventBlock onCompleteCheckout;
- (UIViewController *)parentViewController;
@end
@implementation RNKlarnaView
- (id)initWithBridge:(RCTBridge *)bridge
{
    if ((self = [super init])) {
        self.bridge = bridge;
        NSDictionary* infoDict = [[NSBundle mainBundle] infoDictionary];

        NSString* returnString = [infoDict objectForKey:@"ReturnURLKlarna"];
        NSURL* returnUrl = [NSURL URLWithString:returnString];
        UIViewController *ctrl = [self parentViewController];

        self.checkout = [[KCOKlarnaCheckout alloc] initWithViewController:ctrl returnURL:returnUrl];
        self.klarnaVC = [self.checkout checkoutViewController];
        // self.klarnaVC.internalScrollDisabled = YES;
        // self.klarnaVC.sizingDelegate = self;
        // self.klarnaVC.parentScrollView = ctrl.scrollView;
        [ctrl addChildViewController:_klarnaVC];
        self.klarnaView = _klarnaVC.view;
        [[NSNotificationCenter defaultCenter] addObserver:self 
                                                selector:@selector(handleNotification:) 
                                                  name:KCOSignalNotification 
                                                    object:nil];
    }
    return self;
}
- (void)layoutSubviews
{
    [super layoutSubviews];
    self.klarnaView.frame = self.bounds;
    [self setBackgroundColor:[UIColor blackColor]];
    [self addSubview:self.klarnaView];
}

- (void)removeFromSuperview
{
    [super removeFromSuperview];
    [[NSNotificationCenter defaultCenter] removeObserver:self
                                            name:KCOSignalNotification 
                                              object:nil];
}

-(void)updateSnippet
{
  [self.checkout setSnippet: self.snippet];
}

- (void)onCheckoutComplete:(NSDictionary *)event
{   
  if(_onCompleteCheckout) {
    _onCompleteCheckout(event);
  }
}

- (void)handleNotification:(NSNotification *)notification {
    NSString *name = notification.userInfo[KCOSignalNameKey];
    NSDictionary *data = notification.userInfo[KCOSignalDataKey];
    NSLog(@"Signal name:%@", name);
    NSLog(@"Args: %@", data);
    if ([name isEqualToString:@"complete"]) {
      NSDictionary *completeEvent = @{@"type" : @"completeCheckout", @"data" : data};
      [self onCheckoutComplete: completeEvent];
    }
}

- (UIViewController *)parentViewController {
    UIResponder *responder = self;
    while ([responder isKindOfClass:[UIView class]])
        responder = [responder nextResponder];
    return (UIViewController *)responder;
}
@end
