Java Docs

Robot:
  
  boolean canMove(Direction dir, int distance):
      takes location and returns a boolean to know whether you can move in the specified direction for the distance
      
  void move(Direction dir, int distance):
      takes location and moves in the specifed direction for a distance (doesnt move if canMove returns false)
  
  RobotInfo[] senseNearbyRobots():
      returns a list of robots in your vision radius
  
  RobotInfo[] senseNearbyRobots(char team):
      returns a list of robots in your vision raidus on the specified team
  
  RobotInfo[] senseNearbyRobots(char team, int raidus):
      returns a list of robots that is on the specified team and in a certain radius (doesnt work if radius is more that vision raidus)
  
  void log(String str):
      print the string with your bot's ID and classification

HQ:
  
  boolean canCreate(Location loc, int speed, int health, int attack, int commRange, int visRange, int attackRange):
      takes location and all stats of the robot and returns whether you can create the robot
  
  void create(Location loc, int speed, int health, int attack, int commRange, int visRange, int attackRange):
      takes location and all stats of the obot and creates the robot (doesnt work if canCreate returns false)
  
Direction(int x, int y):
  
  void rotateRight():
      rotates the Direction 45 degrees to the right (only works in Direction is max of 1 in x and y)
  
  void rotateLeft():
      rotates the Direction 45 degrees to the left (only works in Direction is max of 1 in x and y)

Location(int x, int y):

  int distanceTo(Location loc):
      finds the distance from the current location to the specified location
      
  Location add(Direction dir):
      adds the direction to the current Location
  
  Location add(Location loc):
      adds the location to the current location
