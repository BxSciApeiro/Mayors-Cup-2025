package org.firstinspires.ftc.teamcode.auton;

import com.pedropathing.follower.Follower;
import com.pedropathing.localization.Pose;
import com.pedropathing.pathgen.BezierCurve;
import com.pedropathing.pathgen.BezierLine;
import com.pedropathing.pathgen.Path;
import com.pedropathing.pathgen.PathChain;
import com.pedropathing.pathgen.Point;
import com.pedropathing.util.Constants;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import  com.qualcomm.robotcore.eventloop.opmode.OpMode;

import pedroPathing.constants.FConstants;
import pedroPathing.constants.LConstants;

@Autonomous(name = "pedroTest")
public class pedroTest extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private int pathState;

    private final double forwardDirection = Math.toRadians(0);
    private final double backDirection = Math.toRadians(180);
    private final Pose startPose = new Pose(8, 50, forwardDirection);
    private final Pose scorePose = new Pose(40, 65);
    private final Pose samplesPose = new Pose(40, 36);
    private final Pose sampleOnePose = new Pose(15, 25);
    private final Pose sampleTwoPose = new Pose(15, 15);
    private final Pose lineUpPose = new Pose(10, 25);
    private final Pose scoreTwoPose = new Pose(40, 65);
    private final Pose parkPose = new Pose(10, 35);

    private Path scorePreload, lineUp, scoreTwo, toPark;
    private PathChain samples;

    public void buildPaths() {
        scorePreload = new Path(new BezierCurve(
                new Point(startPose), new Point(20, 60), new Point(scorePose)
        ));
        scorePreload.setConstantHeadingInterpolation(0);

        lineUp = new Path(new BezierCurve(
                new Point(sampleTwoPose), new Point(25, 25), new Point(lineUpPose)
        ));
        lineUp.setLinearHeadingInterpolation(forwardDirection, backDirection);

        scoreTwo = new Path(new BezierCurve(
                new Point(lineUpPose), new Point(40, 40), new Point(15, 60), new Point(scoreTwoPose)
        ));
        scoreTwo.setLinearHeadingInterpolation(backDirection, forwardDirection);

        toPark = new Path(new BezierCurve(
                new Point(scoreTwoPose), new Point(15, 60), new Point(40, 40), new Point(parkPose)
        ));
        toPark.setLinearHeadingInterpolation(forwardDirection, backDirection);

        samples = follower.pathBuilder()
                .addPath(new BezierCurve(
                        new Point(scorePose), new Point(25, 45), new Point(samplesPose)
                ))
                .addPath(new BezierCurve(
                        new Point(samplesPose), new Point(70, 36), new Point(70, 21), new Point(sampleOnePose)
                ))
                .addPath(new BezierCurve(
                        new Point(sampleOnePose), new Point(75, 30), new Point(75, 10), new Point(sampleTwoPose)
                ))
                .setConstantHeadingInterpolation(0)
                .setPathEndTimeoutConstraint(3.0) // Timeout for the entire chain
                .build();
    }

    public void autonomousPathUpdate() {
        switch (pathState) {
            case 0:
                follower.followPath(scorePreload);
                setPathState(1);
                break;
            case 1:
                if (!follower.isBusy()) {
                    follower.followPath(samples, true);
                    setPathState(2);
                }
                break;
            case 2:
                if (!follower.isBusy()) {
                    follower.followPath(lineUp);
                    setPathState(3);
                }
                break;
            case 3:
                if (!follower.isBusy()) {
                    follower.followPath(scoreTwo);
                    setPathState(4);
                }
                break;
            case 4:
                if (!follower.isBusy()) {
                    follower.followPath(toPark);
                    setPathState(-1);
                }
                break;
        }
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }

    @Override
    public void init() {
        pathTimer = new Timer();
        Constants.setConstants(FConstants.class, LConstants.class);
        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
        buildPaths();
    }

    @Override
    public void loop() {
        follower.update();
        autonomousPathUpdate();
        telemetry.addData("Path State", pathState);
        telemetry.addData("Position", follower.getPose().toString());
        telemetry.addData("Timer", pathTimer);
        telemetry.update();
    }
}
