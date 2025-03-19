package org.firstinspires.ftc.teamcode.util;

import dev.frozenmilk.dairy.core.wrapper.Wrapper;

public class constants {
    public static boolean ifOpMode(Wrapper OpMode) {
        return OpMode.getState() == Wrapper.OpModeState.STOPPED;
    }

    public static final class slides {
        public enum linkageState {
            HIGH_BAR,
            PLACE_SPEC,
            WALL,
            REST,
            CONTROLLABLE
        }

        public static final String LEFTLINKAGE = "leftLinkage";
        public static final String RIGHTLINKAGE = "rightLinkage";
        public static final double BOTTOMLIMIT = 50;

        public static double SlidesP = 0.001;
        public static double SlidesI = 0.1;
        public static double SlidesD = 0.0001;

        public static final double highBarPos = -5300;
        public static final double placeSpecPos = 4000;
        public static final double wallPos = 900;
        public static final double restPos = 100;
    }
}
// add FTC Dashboard stuff (@Config)