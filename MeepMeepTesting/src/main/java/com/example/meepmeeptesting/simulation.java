package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class simulation {
    public static void main(String[] args) {
        int initialX = 25;
        int initialY = -65;
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d firstLeft = new Vector2d(initialX - 15, initialY);
        Vector2d rightForward = new Vector2d(initialX - 15, initialY + 25);
        Vector2d moreForward = new Vector2d(initialX - 15, initialY + 27);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, initialY, Math.toRadians(90)))
                .strafeTo(firstLeft)
                .strafeTo(rightForward)
                .strafeTo(moreForward)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}