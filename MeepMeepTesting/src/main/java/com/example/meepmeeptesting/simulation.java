package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class simulation {
    public static int frontY = -5;
    public static int backY = -65;
    public static int initialX = -36;

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);

        Vector2d forwardMove = new Vector2d(initialX, frontY);
        Vector2d leftMove = new Vector2d(initialX - 20, frontY);
        Vector2d downMove = new Vector2d(initialX - 20, backY + 1);
        Vector2d forwardMoveTwo = new Vector2d(initialX - 20, frontY);
        Vector2d leftMoveTwo = new Vector2d(initialX - 30, frontY);
        Vector2d downMoveTwo = new Vector2d(initialX - 30, backY + 1);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(initialX, backY, Math.toRadians(90)))
                .strafeTo(forwardMove)
                .strafeTo(leftMove)
                .strafeTo(downMove)
                .strafeTo(forwardMoveTwo)
                .strafeTo(leftMoveTwo)
                .strafeTo(downMoveTwo)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}