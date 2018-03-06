import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.util.Delay;

import java.util.ArrayList;;

public class Robot {
	private UltrasonicSensor us;
	private NXTRegulatedMotor motorA;
    private NXTRegulatedMotor motorB;
    private NXTRegulatedMotor motorC;
    String looking_at;
    public Node my_pos;
    
    int TURN_90_ANGLE = 540;
	int MOVE_1_STEP_ANGLE = 850;
    
    public Robot(Node n) {
    	us = new UltrasonicSensor(SensorPort.S4);
    	motorA = Motor.A;
    	motorB = Motor.B;
    	motorC = Motor.C;
    	motorA.setSpeed(100);
    	looking_at = "F";
    	my_pos = n;
    }
    
    public ArrayList<Boolean> neighbors() {
    	ArrayList<Boolean> neighbors = new ArrayList<Boolean>();
    	neighbors.add(lookRight());
    	neighbors.add(lookLeft());
    	neighbors.add(lookFront());
    	for(Boolean b : neighbors) System.out.println(b);
    	return neighbors;
    }
    
    //Return true if step is free
    public Boolean lookFront() {
    	motorA.rotateTo(0, true);

    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Front:" + distance);
    	
    	return distance > 20;
    }
    
    //Return true if step is free
    public Boolean lookLeft() {
    	motorA.rotateTo(91, true);
    	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Left:" + distance);

    	return distance > 20;
    }
    
    //Return true if step is free
    public Boolean lookRight() {
    	motorA.rotateTo(-91, true);
 	
    	int distance = 0;
    	while (motorA.isMoving());
    	Delay.msDelay(500);
    	distance = us.getDistance();
    	System.out.println("Right:" + distance);

    	return distance > 20;
    }
    
    public void moveTo(Node goal, MyMap came_from) {
    	Node current = goal;
    	System.out.println(looking_at);
    	while (!my_pos.equals(goal)) {
    		if(Math.abs(current.x - my_pos.x) == 1 || Math.abs(current.y - my_pos.y) == 1) {
    			moveOneSpot(current);
    		} else {
    			current = (Node) came_from.get(current);
    		}
    	}
    	
    }
    
    public void moveOneSpot(Node goal) {
    	System.out.println("Moving to: " + goal);
    	// Move horizontally
    	if(goal.x == my_pos.x) {
    		if(goal.y > my_pos.y) { // Move right
    			my_pos = goal;
    			if(looking_at.equals("F")) {
    				rotateRight();
    				moveForward();
    				looking_at = "R";
    				return;
    			}
    			if(looking_at.equals("L")) {
    				rotateRight();
    				rotateRight();
    				moveForward();
    				looking_at = "R";
    				return;
    			}
    			if(looking_at.equals("R")) {
    				moveForward();
    				return;
    			}
    			if(looking_at.equals("B")) {
    				rotateLeft();
    				moveForward();
    				looking_at = "R";
    				return;
    			}
    		} else { // Move left
    			my_pos = goal;
    			if(looking_at.equals("F")) {
    				rotateLeft();
    				moveForward();
    				looking_at = "L";
    				return;
    			}
    			if(looking_at.equals("L")) {
    				moveForward();
    				return;
    			}
    			if(looking_at.equals("R")) {
    				rotateLeft();
    				rotateLeft();
    				moveForward();
    				looking_at = "L";
    				return;
    			}
    			if(looking_at.equals("B")) {
    				rotateRight();
    				moveForward();
    				looking_at = "L";
    				return;
    			}
    		}
    	}
    	// Move vertically
    	if(goal.y == my_pos.y) {
    		if(goal.x > my_pos.x) { // Move down
    			my_pos = goal;
    			if(looking_at.equals("F")) {
    				rotateRight();
    				rotateRight();
    				moveForward();
    				looking_at = "B";
    				return;
    			}
    			if(looking_at.equals("L")) {
    				rotateLeft();
    				moveForward();
    				looking_at = "B";
    				return;
    			}
    			if(looking_at.equals("R")) {
    				rotateRight();
    				moveForward();
    				looking_at = "B";
    				return;
    			}
    			if(looking_at.equals("B")) {
    				moveForward();
    				return;
    			}
    		} else { // Move up
    			my_pos = goal;
    			if(looking_at.equals("F")) {
    				moveForward();
    				return;
    			}
    			if(looking_at.equals("L")) {
    				rotateRight();
    				moveForward();
    				looking_at = "F";
    				return;
    			}
    			if(looking_at.equals("R")) {
    				rotateLeft();
    				moveForward();
    				looking_at = "F";
    				return;
    			}
    			if(looking_at.equals("B")) {
    				rotateLeft();
    				rotateLeft();
    				moveForward();
    				looking_at = "F";
    				return;
    			}
    		}
    	}
    }
    
    public void rotateRight() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	motorB.rotateTo(TURN_90_ANGLE, true);
    	motorC.rotateTo(-TURN_90_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    }
    
    public void rotateLeft() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	motorB.rotateTo(-TURN_90_ANGLE, true);
    	motorC.rotateTo(TURN_90_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    }
    
    public void moveForward() {
    	motorB.resetTachoCount();
    	motorC.resetTachoCount();
    	
    	Motor.B.rotate(MOVE_1_STEP_ANGLE, true);
    	Motor.C.rotate(MOVE_1_STEP_ANGLE, true);
    	
    	while (motorB.isMoving() || motorC.isMoving());
    }
    
}
