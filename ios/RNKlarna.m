#import "RNKlarna.h"
#import "RNKlarnaView.h"
#import <React/RCTViewManager.h>
#import <React/RCTBridge.h>
#import <React/RCTEventDispatcher.h>
#import <React/RCTUtils.h>
#import <React/UIView+React.h>
#import <React/RCTUIManager.h>

@implementation RNKlarna

RCT_EXPORT_VIEW_PROPERTY(onCompleteCheckout, RCTBubblingEventBlock);

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (NSArray<NSString *> *)supportedEvents
{
    return @[@"completeCheckout"];
}

- (UIView *)view
{   
    return [[RNKlarnaView alloc] initWithBridge:self.bridge];
}

RCT_CUSTOM_VIEW_PROPERTY(snippet, NSString *, RNKlarnaView)
{   
    [view setSnippet:[RCTConvert NSString:json]];
    [view updateSnippet];
}

RCT_EXPORT_MODULE()

@end
  
