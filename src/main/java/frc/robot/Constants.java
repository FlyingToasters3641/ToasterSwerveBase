package frc.robot;

public final class Constants {
    public static final Mode currentMode = Mode.REAL;
    public static boolean DeterministicTimestamps = true;
    public static enum Mode {
        /** Running on a real robot. */
        REAL,
        /** Running a physics simulator. */
        SIM,
        /** Replaying from a log file. */
        REPLAY
    }


}
