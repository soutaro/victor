package com.dhemery.victor.device;

public class IosDeviceConfigurationOptions {
    private IosDeviceConfigurationOptions(){}

    /**
     * The absolute path to the iOS application binary file to execute.
     * This is typically a file inside the application's .app package.
     * The file's executable flag must be set.
     */
    public static final String APPLICATION_BINARY_PATH = "victor.application.binary.path";

    /**
     * The type of device to simulate.
     * See your iOS Simulator's Device menu for possible values.
     */
    public static final String DEVICE_TYPE = "victor.simulator.device.type";

    /**
     * The absolute path to the root directory of the iOS SDK with which to launch the simulator.
     */
    public static final String SDK_ROOT = "victor.sdk.root";

    /**
     * The absolute path to the iOS Simulator executable file.
     */
    public static final String SIMULATOR_BINARY_PATH = "victor.simulator.binary.path";

    /**
     * <p>Specifies who is responsible for starting and stopping the simulator.</p>
     * <p>If the value is <strong>victor</strong>,
     * the constructed {@link com.dhemery.victor.IosDevice IosDevice}'s
     * {@link com.dhemery.victor.IosDevice#start() start()} method will launch the simulator,
     * and its {@link com.dhemery.victor.IosDevice#stop() stop()} method will shut it down.
     * </p>
     * <p>
     * If this option has any other value,
     * the constructed {@link com.dhemery.victor.IosDevice IosDevice}
     * will neither start nor stop the simulator.
     * Instead, the user must start and stop the simulator in some other way.
     * This is useful for experimenting.
     * It allows you to launch the application on your own,
     * manually execute preparatory steps to bring the application to a desired state,
     * then run an automated test against the prepared application.
     * </p>
     */
    public static final String SIMULATOR_PROCESS_OWNER = "victor.simulator.process.owner";
}
