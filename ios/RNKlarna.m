
#import "RNKlarna.h"
#import <React/RCTViewManager.h>

@implementation RNKlarna

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (UIView *)view
{   
    NSDictionary* infoDict = [[NSBundle mainBundle] infoDictionary];

    NSString* returnString = [infoDict objectForKey:@"ReturnURLKlarna"];
    NSURL* returnUrl = [NSURL URLWithString:returnString];
    UIViewController *ctrl = RCTPresentedViewController();
    self.checkout = [[KCOKlarnaCheckout alloc] initWithViewController:ctrl returnURL:returnUrl];
    return _checkout.view;
}

RCT_CUSTOM_VIEW_PROPERTY(snippet, NSString *, RNKlarna)
{   
    json = [RCTConvert NSString:json];
    self.checkout.snippet = json;
}

RCT_EXPORT_MODULE()

@end
  