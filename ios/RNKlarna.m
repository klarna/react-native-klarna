
#import "RNKlarna.h"
#import <React/RCTViewManager.h>

@interface RNKlarna : RCTViewManager
@end

@implementation RNKlarna

+ (BOOL)requiresMainQueueSetup
{
    return YES;
}

- (UIView *)view
{   
    NSDictionary* infoDict = [[NSBundle mainBundle] infoDictionary];
    NSString* returnUrl = [infoDict objectForKey:@"ReturnURLKlarna"];
    return [[KCOKlarnaCheckout alloc] initWithViewController:self returnURL:returnUrl];
}

RCT_CUSTOM_VIEW_PROPERTY(snippet, NSString *, RNKlarna)
{   
    json = RCTConvert NSString:json];
    view.snippet = json;
}

RCT_EXPORT_MODULE()

@end
  