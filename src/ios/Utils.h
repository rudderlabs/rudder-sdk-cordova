#import <Foundation/Foundation.h>
#import <Rudder/Rudder.h>

@interface Utils : NSObject

+ (RSOption *) getRudderOption: (NSDictionary *) option;
+ (RSConfig *) getRudderConfig: (NSDictionary *) config;
+ (NSString *) getString:(NSObject *) object;
+ (int) getInteger: (NSObject *) object;

@end


