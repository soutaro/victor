package com.dhemery.victor.configuration;

import com.dhemery.configuration.Configuration;
import com.dhemery.victor.discovery.IosSdk;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.dhemery.victor.configuration.IosDeviceConfigurationOptions.APPLICATION_BUNDLE_PATH;
import static com.dhemery.victor.configuration.IosDeviceConfigurationOptions.SDK_VERSION;

/**
 * Finds the most preferred SDK based on configuration options.
 */
public class FindSdk {
    private static Logger log = LoggerFactory.getLogger(FindSdk.class);

    /**
     * <p>Search this computer for an installed SDK in this order of preference:</p>
     * <ol>
     * <li>The SDK version specified by the {@link com.dhemery.victor.configuration.IosDeviceConfigurationOptions#SDK_VERSION SDK_VERSION} option
     * in the configuration, if any.</li>
     * <li>The SDK version used to build the application, if specified in the application bundle.</li>
     * <li>The latest version SDK installed on this computer.</li>
     * </ol>
     * @param configuration a configuration that identifies an application bundle and optionally an SDK version.
     * @return the most preferred SDK that is installed on this computer.
     * @throws com.dhemery.victor.configuration.IosDeviceConfigurationException if no SDK is installed on this computer.
     */
    public static IosSdk withConfiguration(Configuration configuration) {
        IosSdk sdk;
        if(configuration.defines(SDK_VERSION)) {
            String version = configuration.option(SDK_VERSION);
            sdk = IosSdk.withVersion(version);
            if(sdk.isInstalled()) return sdk;
        }

        IosApplicationBundle bundle = new IosApplicationBundle(configuration.requiredOption(APPLICATION_BUNDLE_PATH));
        String canonicalName = bundle.sdkCanonicalName();
        if(canonicalName != null) {
            sdk = IosSdk.withCanonicalName(canonicalName);
            if(sdk.isInstalled()) return sdk;
        }

        sdk = IosSdk.newest();
        if(sdk.isInstalled()) return sdk;

        throw new IosDeviceConfigurationException("No iphonesimulator SDK installed on this computer");
    }
}
