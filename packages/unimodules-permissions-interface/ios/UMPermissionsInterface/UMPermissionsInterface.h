// Copyright 2018-present 650 Industries. All rights reserved.

#import <Foundation/Foundation.h>
#import <UMCore/UMModuleRegistry.h>

@protocol UMPermissionsInterface;

typedef enum UMPermissionStatus {
  UMPermissionStatusDenied,
  UMPermissionStatusGranted,
  UMPermissionStatusUndetermined,
} UMPermissionStatus;


@protocol UMPermissionsRequester <NSObject>

+ (NSString *)permissionType;

- (void)requestPermissionsWithResolver:(UMPromiseResolveBlock)resolve rejecter:(UMPromiseRejectBlock)reject;

- (NSDictionary *)getPermissions;

@end

@protocol UMPermissionsInterface

- (NSDictionary *)getPermissionsForResource:(NSString *)resource;

- (BOOL)hasGrantedPermission:(NSString *)permissionType;

- (void)askForPermission:(NSString *)permissionType
              withResult:(void (^)(NSDictionary *))onResult
            withRejecter:(UMPromiseRejectBlock)reject;

- (void)registerRequesters:(NSSet<id<UMPermissionsRequester>> *)newRequesters;

- (NSDictionary *)getPermissionWithRequesterClass:(Class)requesterClass;

- (BOOL)hasGrantedPermissionWithRequesterClass:(Class)requesterClass;

- (void)askForPermissionWithRequesterClass:(Class)requesterClass
              withResult:(void (^)(NSDictionary *))onResult
            withRejecter:(UMPromiseRejectBlock)reject;

@end
