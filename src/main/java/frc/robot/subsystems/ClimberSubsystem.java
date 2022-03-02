// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClimberSubsystem extends SubsystemBase {
  private static Spark climber_left = new Spark(6);
  private static Spark climber_right = new Spark(5);

  public ClimberSubsystem() {}


  public void move(double speed){
    if(speed >= .1 || speed <= -.1){
      climber_left.set(speed);
      climber_right.set(speed);
    }else{
      stop();
    }
  }
  public void moveLeft(double speed){
    if(speed >= .1 || speed <= -.1){
      climber_left.set(speed);
    }
    else{
      climber_left.set(0);

    }
  }
  public void moveRight(double speed){
    if(speed >= .1 || speed <= -.1){
      climber_right.set(speed);
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
    // This method will be called once per scheduler run
  }
}
