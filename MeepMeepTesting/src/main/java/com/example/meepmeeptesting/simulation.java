package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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

        Vector2d specPos = new Vector2d(initialX - 15, initialY + 30);
        Vector2d specPush = new Vector2d(initialX - 15, initialY + 32);
        Vector2d specBack = new Vector2d(initialX - 15, initialY + 25);

        Vector2d toSamplesOne = new Vector2d(initialX + 10, initialY + 30);
        Vector2d toSamplesTwo = new Vector2d(initialX + 20, frontY);
        Vector2d toSamplesThree = new Vector2d(initialX + 25, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX + 25, initialY + 10);
        Vector2d sampleOneReturn = new Vector2d(initialX + 25, frontY);
        Vector2d sampleOneToSampleTwo = new Vector2d(initialX + 35, frontY);
        Vector2d sampleTwoPush = new Vector2d(initialX + 35, initialY + 10);
        Vector2d specLineUpOne = new Vector2d(initialX + 25, initialY + 20);
        Vector2d specLineUpTwo = new Vector2d(initialX + 25, initialY);
        Vector2d specLineUpThree = new Vector2d(initialX + 25, initialY + 15);

        Vector2d specTwoPos = new Vector2d(initialX - 17, initialY + 30);
        Vector2d specTwoPush = new Vector2d(initialX - 17, initialY + 32);
        Vector2d specTwoBack = new Vector2d(initialX - 17, initialY + 25);

        Vector2d park = new Vector2d(initialX + 25, initialY + 7);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, initialY, Math.toRadians(90)))
                .splineToConstantHeading(specPos, Math.toRadians(90))
                .strafeTo(specPush)

                .strafeTo(specBack)
                .splineToConstantHeading(toSamplesOne, Math.toRadians(5))
                .splineToConstantHeading(toSamplesTwo, Math.toRadians(350))
                .splineToConstantHeading(toSamplesThree, Math.toRadians(270))
                .strafeTo(sampleOnePush)
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneToSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .setTangent(Math.toRadians(90))
                .splineTo(specLineUpOne, Math.toRadians(270))
                .strafeTo(specLineUpTwo)
                .strafeTo(specLineUpThree)

                .setTangent(Math.toRadians(-90))
                .splineTo(specTwoPos, Math.toRadians(90))
                .strafeTo(specTwoPush)
                .strafeTo(specTwoBack)

                .splineToConstantHeading(park, Math.toRadians(270), new TranslationalVelConstraint(100))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}