 
#if __has_include("RCTBridgeModule.h")
#import "RCTBridgeModule.h"
#else
#import <React/RCTBridgeModule.h>
#endif
#import <React/RCTViewManager.h>
#import <KlarnaCheckoutSDK/KlarnaCheckout.h>

@interface RNKlarna : RCTViewManager <RCTBridgeModule>

@property (nonatomic) KCOKlarnaCheckout *checkout;

@end
  