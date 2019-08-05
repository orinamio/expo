package expo.modules.notifications.scoper;

import org.unimodules.core.interfaces.InternalModule;

import java.util.Collections;
import java.util.List;

public class BareStringScoper implements InternalModule, StringScoper {

    @Override
    public String getScopedString(String s) {
        return s;
    }

    @Override
    public List<? extends Class> getExportedInterfaces() {
        return Collections.singletonList(StringScoper.class);
    }

}
