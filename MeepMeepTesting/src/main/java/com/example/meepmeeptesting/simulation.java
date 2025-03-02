package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

public class simulation {
    public static void main(String[] args) {
        int initialX = -36;
        int initialY = -65;
        int frontY = -2;
        int backY = -55;
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d forwardMove = new Vector2d(initialX, frontY);
        Vector2d toSampleOne = new Vector2d(initialX - 18, frontY);
        Vector2d sampleOnePush = new Vector2d(initialX - 18, backY);
        Vector2d sampleOneReturn = new Vector2d(initialX - 18, frontY);
        Vector2d toSampleTwo = new Vector2d(initialX - 32, frontY);
        Vector2d sampleTwoPush = new Vector2d(initialX - 32, backY);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, initialY, Math.toRadians(90)))
                .splineToConstantHeading(forwardMove, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(toSampleOne, Math.toRadians(270))
                .splineToConstantHeading(sampleOnePush, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(sampleOneReturn, Math.toRadians(90), new TranslationalVelConstraint(100))
                .splineToConstantHeading(toSampleTwo, Math.toRadians(270))
                .splineToConstantHeading(sampleTwoPush, Math.toRadians(90))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}