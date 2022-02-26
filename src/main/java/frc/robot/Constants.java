// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public final class Constants {
    public static LeboticsController driver1 = new LeboticsController( new XboxController(0));
    public static LeboticsController driver2 = new LeboticsController( new XboxController(1));

    public static Spark shooter_motor = new Spark(9);
    public static Encoder shooter_encoder = new Encoder(9,8);
    private static final double counts_per_revolution = 5; 
    private static final double wheel_diameter = 6; 
    public static final PIDController shooter_pid = new PIDController(0, 0, 0);
    public static final Servo left_servo = new Servo(7);
    public static final Servo right_servo = new Servo(6);


    public static Spark intake_servo = new Spark(8);
    public static DigitalInput intake_upper_switch = new DigitalInput(7);
    public static DigitalInput intake_lower_switch = new DigitalInput(6);
    public static Spark intake_motor = new Spark(7);


    private static VictorSPX chassis_motor_left1 = new VictorSPX(1);
    private static VictorSPX chassis_motor_left2 = new VictorSPX(2);
    private static VictorSPX chassis_motor_right1 = new VictorSPX(0);
    private static VictorSPX chassis_motor_right2 = new VictorSPX(3);
    
    
    public static Spark climber_left = new Spark(6);
    public static Spark climber_right = new Spark(5);


    public Constants(){
        Constants.shooter_encoder.setDistancePerPulse(Math.PI*Constants.wheel_diameter/Constants.counts_per_revolution); //distance per pulse is pi* (wheel diameter / counts per revolution)
        
        //chassis_motor_right1.setInverted(true);
        //chassis_motor_left1.setInverted(true);
        //chassis_motor_right2.follow(chassis_motor_right1);
        //chassis_motor_left2.follow(chassis_motor_left1);

        //chassis_motor_right2.setInverted(InvertType.FollowMaster);
        //chassis_motor_left2.setInverted(InvertType.FollowMaster);
    }

    public static void drive(double speed1, double speed2){
        chassis_motor_left1.set(ControlMode.PercentOutput ,speed1);
        chassis_motor_left2.set(ControlMode.PercentOutput, speed1);
        chassis_motor_right1.set(ControlMode.PercentOutput ,-speed2);
        chassis_motor_right2.set(ControlMode.PercentOutput ,-speed2);
    }



}
