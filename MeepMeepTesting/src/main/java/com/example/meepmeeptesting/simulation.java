package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class simulation {
    public static void main(String[] args) {
        int initialX = 20;
        int initialY = -65;
        int frontY = -15;
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d specPos = new Vector2d(initialX - 10, initialY + 25);
        Vector2d specPush = new Vector2d(initialX - 10, initialY + 31);
        Vector2d specBack = new Vector2d(initialX - 10, initialY + 25);

        Vector2d sampleSplineOne = new Vector2d(initialX + 15, initialY + 31);
        Vector2d sampleSplineTwo = new Vector2d(initialX + 15, frontY);
        Vector2d sampleOneSpline = new Vector2d(initialX + 25, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX + 25, initialY + 5);
        Vector2d sampleOneBack = new Vector2d(initialX + 25, frontY);
        Vector2d sampleTwoSpline = new Vector2d(initialX + 35, frontY);
        Vector2d sampleTwoPush = new Vector2d(initialX + 35, initialY + 5);

        Vector2d specPosTwo = new Vector2d(initialX + 25, initialY + 5);
        Vector2d specTwoGet = new Vector2d(initialX + 25, initialY + 3);
        Vector2d park = new Vector2d(initialX + 25, initialY + 7);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, initialY, Math.toRadians(90)))
                .splineToConstantHeading(specPos, Math.toRadians(90))
                .strafeTo(specPush)
                .strafeTo(specBack)
                .splineToConstantHeading(sampleSplineOne, Math.toRadians(90))
                .strafeTo(sampleSplineTwo)
                .splineToConstantHeading(sampleOneSpline, Math.toRadians(270))
                .strafeTo(sampleOnePush)
                .strafeTo(sampleOneBack)
                .splineToConstantHeading(sampleTwoSpline, Math.toRadians(270))
                .strafeTo(sampleTwoPush)
                .splineTo(specPosTwo, Math.toRadians(-270))
                .strafeTo(specTwoGet)
                .strafeTo(specPos)
                .turn(Math.toRadians(180))
                .strafeTo(specPush)
                .strafeTo(specBack)
                .strafeTo(park)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}