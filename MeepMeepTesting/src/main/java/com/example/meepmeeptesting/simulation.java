package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class simulation {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(600);
        Vector2d rightVector = new Vector2d(35  , -65);
        Vector2d ForwardVector = new Vector2d(35, -10);
        Vector2d block1right = new Vector2d(46, -10);
        Vector2d down1 = new Vector2d(46, -60);
        Vector2d block2right = new Vector2d(56, -10);
        Vector2d down2 = new Vector2d(56, -60);

        

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 17.75)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(25, -65, 0))
                .strafeTo(rightVector)
                .strafeTo(ForwardVector)
                .strafeTo(block1right)
                .strafeTo(down1)
                .strafeTo(block1right)
                .strafeTo(block2right)
                .strafeTo(down2)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}