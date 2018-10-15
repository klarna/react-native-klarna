#import <React/RCTBridge.h>
#import <React/RCTBridgeModule.h>
#import <UIKit/UIKit.h>
#import <KlarnaCheckoutSDK/KlarnaCheckout.h>

@class RNKlarnaView;

@interface RNKlarnaView : UIView
@property (nonatomic) UIView *klarnaView;
@property (nonatomic) KCOKlarnaCheckout *checkout;
@property (nonatomic) UIViewController *klarnaVC;
@property (assign, nonatomic) NSString *snippet;
- (id)initWithBridge:(RCTBridge *)bridge;
- (void)updateSnippet;
- (void)onCheckoutComplete:(NSDictionary *)event;
@end