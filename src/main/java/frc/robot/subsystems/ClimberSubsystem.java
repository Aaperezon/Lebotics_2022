// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  private static final CANSparkMax climber_left = new CANSparkMax(8, MotorType.kBrushless);
  private static final CANSparkMax climber_right = new CANSparkMax(9, MotorType.kBrushless);

  private static DigitalInput left_upper_limit = new DigitalInput(6);
  private static DigitalInput left_lower_limit = new DigitalInput(7);

  private static DigitalInput right_upper_limit = new DigitalInput(8);
  private static DigitalInput right_lower_limit = new DigitalInput(9);




  public ClimberSubsystem() {}
 


 
  public void move(double speed){
    if(speed >= .1 || speed <= -.1){
      moveLeft(speed);
      moveRight(speed);
    }else{
      stop();
    }
  }
  public void moveLeft(double speed){
    if(speed >= .1 && left_lower_limit.get()){
      System.out.println(left_lower_limit.get());
      climber_left.set(speed);
    }else if(speed <= -.1 && left_upper_limit.get()){
      climber_left.set(speed);
    }
    else{
      climber_left.set(0);
    }
  }
  public void moveRight(double speed){
    if(speed >= .1 && right_lower_limit.get()){
      climber_right.set(-speed);
    }else if(speed <= -.1 && right_upper_limit.get()){
      climber_right.set(-speed);
    }
    else{
      climber_right.set(0);
    }

  }

  public void stop(){
    climber_left.set(0);
    climber_right.set(0);
  }


  @Override
  public void periodic() {
  }
}
