package expo.modules.permissions.requesters

import android.Manifest
import android.os.Bundle
import expo.modules.permissions.PermissionsService
import expo.modules.permissions.DENIED_VALUE
import expo.modules.permissions.EXPIRES_KEY
import expo.modules.permissions.GRANTED_VALUE
import expo.modules.permissions.PERMISSION_EXPIRES_NEVER
import expo.modules.permissions.PermissionsTypes.CALENDAR
import expo.modules.permissions.STATUS_KEY
import expo.modules.permissions.UNDETERMINED_VALUE

class CalendarRequester : PermissionRequester {
  override fun getPermissionToAsk(): Array<String> {
    return arrayOf(
        Manifest.permission.READ_CALENDAR,
        Manifest.permission.WRITE_CALENDAR
    )
  }

  override fun getPermission(): Bundle {
    return Bundle().apply {
      try {
        when {
          PermissionRequester.arePermissionsGranted(getPermissionToAsk()) -> {
            putString(STATUS_KEY, GRANTED_VALUE)
          }
          PermissionsService.didAsk(CALENDAR.type) -> {
            putString(STATUS_KEY, DENIED_VALUE)
          }
          else -> {
            putString(STATUS_KEY, UNDETERMINED_VALUE)
          }
        }
      } catch (e: IllegalStateException) {
        putString(STATUS_KEY, DENIED_VALUE)
      }
      putString(EXPIRES_KEY, PERMISSION_EXPIRES_NEVER)
    }
  }
}