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
        int initialX = 20;
        int initialY = -65;
        int frontY = 9;
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d specLineUpOne = new Vector2d(initialX + 35, initialY + 30);
        Vector2d specLineUpTwo = new Vector2d(initialX + 35, initialY - 2);

        Vector2d specTwoPosOne = new Vector2d(initialX + 35, initialY + 15);
        Vector2d specTwoPosTwo = new Vector2d(initialX - 20, frontY + 2);
        Vector2d specTwoBack = new Vector2d(initialX - 20, frontY - 10);

        Vector2d park = new Vector2d(initialX + 30, initialY + 40);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, initialY, Math.toRadians(90)))
                .strafeTo(specTwoPosOne)
                .setTangent(Math.toRadians(-90))
                .splineTo(specTwoPosTwo, Math.toRadians(70), new TranslationalVelConstraint(100))
                .strafeTo(specTwoBack)
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(park, Math.toRadians(270), new TranslationalVelConstraint(100))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}