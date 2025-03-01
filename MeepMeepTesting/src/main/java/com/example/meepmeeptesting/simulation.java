package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class simulation {
    public static Vector2d reflect(Vector2d a) {
        double reflectedx = -a.x;
        return new Vector2d(reflectedx, a.y);
    }
    public static void main(String[] args) {
        int initialX = 20;
        int initialY = -65;
        int frontY = 5;
        int initialX2 = -20;
        int initialY2 = -65;
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d specPos = new Vector2d(initialX - 25, initialY + 30);
        Vector2d specPush = new Vector2d(initialX - 25, initialY + 41);
        Vector2d specBack = new Vector2d(initialX - 25, initialY + 25);
        Vector2d basket = new Vector2d(-60, -60);
        Vector2d basketOut = new Vector2d(-60, -40);
        Vector2d toSamplesOne = new Vector2d(initialX + 5, initialY + 30);
        Vector2d LeftToSamplesOne = new Vector2d(-toSamplesOne.x-10, toSamplesOne.y);
        Vector2d toSamplesTwo = new Vector2d(initialX + 10, frontY);
        Vector2d LefToSamplesTwo = new Vector2d(-35, -40);
        Vector2d toSamplesThree = new Vector2d(initialX + 30, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX + 30, initialY + 20);
        Vector2d sampleOneReturn = new Vector2d(initialX + 30, frontY);
        Vector2d sampleOneToSampleTwo = new Vector2d(initialX + 48, frontY + 8);
        Vector2d sampleTwoPush = new Vector2d(initialX + 48, initialY + 26);
        Vector2d specLineUpOne = new Vector2d(initialX + 35, initialY + 28);
        Vector2d specLineUpTwo = new Vector2d(initialX + 35, initialY - 8);

        Vector2d specTwoPosOne = new Vector2d(initialX + 35, initialY + 25);
        Vector2d specTwoPosTwo = new Vector2d(initialX - 10, frontY - 2);
        Vector2d specTwoBack = new Vector2d(initialX - 10, frontY - 20);

        Vector2d park = new Vector2d(initialX + 75, initialY + 15);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX2, initialY2, Math.toRadians(90)))
                /*
                .splineToConstantHeading(specPos, Math.toRadians(90))
                .strafeTo(specPush)

                .strafeTo(specBack)
                .splineToConstantHeading(toSamplesOne, Math.toRadians(5))
                .splineToConstantHeading(toSamplesTwo, Math.toRadians(90))
                .splineToConstantHeading(toSamplesThree, Math.toRadians(270))
                .splineToConstantHeading(sampleOnePush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneToSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .setTangent(Math.toRadians(90))
                .splineTo(specLineUpOne, Math.toRadians(270))
                .strafeTo(specLineUpTwo)
                .strafeTo(specTwoPosOne)
                .setTangent(Math.toRadians(-90))
                .splineTo(specTwoPosTwo, Math.toRadians(90), new TranslationalVelConstraint(100))
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(specTwoPosTwo, Math.toRadians(90), new TranslationalVelConstraint(100))
                .strafeTo(specTwoBack)

                .splineToConstantHeading(park, Math.toRadians(270), new TranslationalVelConstraint(100))

                 */
                        .setTangent(Math.toRadians(90))
                        .splineTo(basket, Math.toRadians(270))
                        .strafeTo(basketOut)
                        .setTangent(0)
                        .strafeTo(LefToSamplesTwo)
                        .splineToConstantHeading(reflect(toSamplesTwo), Math.toRadians(90))
                        .splineToConstantHeading(reflect(sampleOnePush), Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}