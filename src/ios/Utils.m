#import "Utils.h"

@implementation Utils

+ (RSOption*) getRudderOption: (NSDictionary *) optionsDict {
    RSOption * options = [[RSOption alloc] init];
    if([optionsDict class] == [NSNull class])
    {
        return options;
    }
    if([optionsDict objectForKey:@"externalIds"])
    {
        NSDictionary *externalIdsDict = [optionsDict objectForKey:@"externalIds"];
        for(NSString* key in externalIdsDict)
        {
            [options putExternalId:key withId:[externalIdsDict objectForKey:key]];
        }
    }
    if([optionsDict objectForKey:@"integrations"])
    {
        NSDictionary *integrationsDict = [optionsDict objectForKey:@"integrations"];
        for(NSString* key in integrationsDict)
        {
            [options putIntegration:key isEnabled:[[integrationsDict objectForKey:key] isEqual: @1] ? YES : NO];
        }
    }
    return options;
}

+ (RSConfig *) getRudderConfig: (NSDictionary *) configDict {
    if([configDict class] == [NSNull class])
    {
        return [[RSConfig alloc] init];
    }
    RSConfigBuilder *configBuilder = [[RSConfigBuilder alloc] init ];
    if(configDict[@"dataPlaneUrl"] && [self getString:configDict[@"dataPlaneUrl"]] != nil)
    {
        [configBuilder withDataPlaneUrl:[self getString:configDict[@"dataPlaneUrl"]]];
    }
    if(configDict[@"flushQueueSize"] && [self getInteger:configDict[@"flushQueueSize"]] != -1)
    {
        [configBuilder withFlushQueueSize:[self getInteger:configDict[@"flushQueueSize"]]];
    }
    if(configDict[@"dbCountThreshold"] && [self getInteger:configDict[@"dbCountThreshold"]] != -1)
    {
        [configBuilder withDBCountThreshold:[self getInteger:configDict[@"dbCountThreshold"]]];
    }
    if(configDict[@"sleepTimeOut"] && [self getInteger:configDict[@"sleepTimeOut"]] != -1)
    {
        [configBuilder withSleepTimeOut:[self getInteger:configDict[@"sleepTimeOut"]]];
    }
    if(configDict[@"logLevel"] && [self getInteger:configDict[@"logLevel"]] != -1)
    {
        [configBuilder withLoglevel:[self getInteger:configDict[@"logLevel"]]];
    }
    if(configDict[@"configRefreshInterval"] && [self getInteger:configDict[@"configRefreshInterval"]] != -1)
    {
        [configBuilder withConfigRefreshInteval:[self getInteger:configDict[@"configRefreshInterval"]]];
    }
    if(configDict[@"trackLifecycleEvents"])
    {
        [configBuilder withTrackLifecycleEvens:[configDict[@"trackLifecycleEvents"] boolValue]];
    }
    if(configDict[@"recordScreenViews"])
    {
        [configBuilder withRecordScreenViews:[configDict[@"recordScreenViews"] boolValue]];
    }
    if(configDict[@"controlPlaneUrl"] && [self getString:configDict[@"controlPlaneUrl"]] != nil)
    {
        [configBuilder withControlPlaneUrl:[self getString:configDict[@"controlPlaneUrl"]]];
    }
    return [configBuilder build];
}

+ (NSString *) getString:(NSObject *) object
{
    if([object isKindOfClass:[NSString class]])
    {
        return (NSString *) object;
    }
    if([object isKindOfClass:[NSNumber class]])
    {
        return [(NSNumber *) object stringValue];
    }
    return nil;
}

+ (int) getInteger: (NSObject *) object
{
    if([object isKindOfClass:[NSNumber class]])
    {
        return [(NSNumber *) object intValue];
    }
    if([object isKindOfClass:[NSString class]])
    {
        return [(NSString* ) object intValue];
    }
    return -1;
}

+ (NSDictionary *) getDictionaryFromArguments: (NSArray *) arguments atIndex: (int) index
{
    NSDictionary* argument = [arguments objectAtIndex:index];
    if([argument class] == [NSNull class])
    {
        return nil;
    }
    return argument;
}

+ (NSString *) getStringFromArguments: (NSArray *) arguments atIndex: (int) index
{
    NSString* argument = [arguments objectAtIndex:index];
    if([argument class] == [NSNull class])
    {
        return nil;
    }
    return argument;
    
}


@end
