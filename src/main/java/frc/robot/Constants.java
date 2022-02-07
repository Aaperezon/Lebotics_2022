// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.LeboticsController;
import frc.robot.commands.ShooterCommand;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.XboxController;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static LeboticsController driver1 = new LeboticsController( new XboxController(0));
    public static LeboticsController driver2 = new LeboticsController( new XboxController(1));

    public static Spark shooter_motor = new Spark(9);
    public static Encoder shooter_encoder = new Encoder(8,9);
    private static final double counts_per_revolution = 5; 
    private static final double wheel_diameter = 6; 




    
    public Constants(){
        Constants.shooter_encoder.setDistancePerPulse(Math.PI*Constants.wheel_diameter/Constants.counts_per_revolution); //distance per pulse is pi* (wheel diameter / counts per revolution)

    }



}
