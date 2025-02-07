package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class simulation {
    public static int frontY = 0;
    public static int backY = -60;
    public static int initialX = 36;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d rightPos = new Vector2d(initialX, -65);
        Vector2d forwardPos = new Vector2d(initialX, frontY);
        Vector2d blockRightPos = new Vector2d(initialX + 15, frontY);
        Vector2d blockDownPos = new Vector2d(initialX + 15, backY);
        Vector2d blockUpPos = new Vector2d(initialX + 15, frontY);
        Vector2d blockRightPos2 = new Vector2d(initialX + 25, frontY);
        Vector2d blockDownPos2 = new Vector2d(initialX + 25, backY);
        Vector2d endVector = new Vector2d(56  , -60);
        Vector2d poles = new Vector2d(0, -40);



        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(25, -65, Math.toRadians(90)))
                .strafeTo(rightPos)
                .strafeTo(forwardPos)
                .strafeTo(blockRightPos)
                .strafeTo(blockDownPos)
                .strafeTo(blockUpPos)
                .strafeTo(blockRightPos2)
                .strafeTo(blockDownPos2)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}