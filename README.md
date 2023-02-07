<b><i>Java Docs</i></b>

<b><i>RobotController</i></b>
<ul> 
  <li>boolean canMove(Direction dir, int distance)
      <ul>
          <li>check if you can move in the direction and distance specified (doesnt work if the robot is of type Headquarters)</li>
      </ul>
  </li>
  <li>void move(Direction dir, int distance)
      <ul>
          <li>moves in the direction and distance specified (only works if canMove returns true)</li>
      </ul>
  </li>
  <li>boolean canCreate(int type, Location loc)
      <ul>
          <li>checks if you can spawn the robot that is specified at the location specified</li>
      </ul>
  </li>
  <li>void create(int type, Location loc)
      <ul>
          <li>creates the specified robot at the location (only works if canCreate returns true)</li>
      </ul>
  </li>
  <li>boolean canShoot(Location loc)
      <ul>
          <li>checks if you can shoot at the given location (only works if your robot type is not headquarters)</li>
      </ul>
  </li>
  <li>void shoot(Location loc)
      <ul>
          <li>shoots at the given location</li>
      </ul>
  </li>
  <li>boolean canSendMail(RobotInfo r)
      <ul>
          <li>checks if you can send mail to that specific robot</li>
      </ul>
  </li>
  <li>void sendMail(RobotInfo r, String s)
      <ul>
          <li>sends the mail to the specified Robot</li>
      </ul>
  </li>
  <li>ArrayList<Mail> getMail()
      <ul>
          <li>gets all the mail and clears your mail box</li>
      </ul>
  </li>
  <li>void storeInfo(String str)
      <ul>
          <li>stores the sting into your stores Array</li>
      </ul>
  </li>
  <li>boolean canSendMail(RobotInfo r)
      <ul>
          <li>checks if you can send mail to that specific robot</li>
      </ul>
  </li>
  <li>void sendMail(RobotInfo r)
      <ul>
          <li>sends mail to the specified robot</li>
      </ul>
  </li>
  <li>ArrayList<RobotInfo> senseNearbyRobots()
      <ul>
          <li>returns all the nearby robots</li>
      </ul>
  </li>
  <li>ArrayList<RobotInfo> senseNearbyRobots(char team)
      <ul>
          <li>returns all the nearby robots on the specified team</li>
      </ul>
  </li>
  <li>ArrayList<RobotInfo> senseNearbyRobots(char team, int distance)
      <ul>
          <li>returns all the nearby robots on the specified team with the specified distance(if distance is more than visRange, it doesnt work)</li>
      </ul>
  </li>
  <li>RobotInfo senseRobotById(int id)
      <ul>
          <li>returns the robot with the specific id</li>
      </ul>
  </li>
  <li>ArrayList<WellInfo> senseNearbyWells()
      <ul>
          <li>returns all the nearby wells</li>
      </ul>
  </li>
  <li>ArrayList<WellInfo> senseNearbyWells(int radius)
      <ul>
          <li>returns all the nearby wells in the radius (won't work if radius is bigger than vision range</li>
      </ul>
  </li>
  <li>WellInfo senseWellById(int id)
      <ul>
          <li>returns the well with the specific id</li>
      </ul>
  </li>
  <li>boolean canCollect(int id)
      <ul>
          <li>returns whether you can collect from the well with that specific ID</li>
      </ul>
  </li>
  <li>void collect(int id)
      <ul>
          <li>collects from the well with that specific ID (only works if canCollect returns true</li>
      </ul>
  </li>
  <li>boolean canGive(RobotInfo r)
      <ul>
          <li>returns whether or not you can give all your resources to that specific robot</li>
      </ul>
  </li>
  <li>void give(RobotInfo r)
      <ul>
          <li>gives the resources to that specific robot (only works if canGive returns true</li>
      </ul>
  </li>
</ul>

<b><i>Direction(int x, int y)</i></b>
<ul>
  <li>void rotateRight()
      <ul>
          <li>rotates the current Direction to the right 45 degrees</li>
      </ul>
  </li>
  <li>void rotateLeft()
      <ul>
          <li>rotates the current Direction to the left 45 degrees</li>
      </ul>
  </li>
</ul>

<b><i>Location(int x, int y)</i></b>
<ul>
  <li>Location add(Location loc)
      <ul>
          <li>adds the specified location to the current Location</li>
      </ul>
  </li>
  <li>Location add(Direction dir)
      <ul>
          <li>adds the specified direction to the current Location</li>
      </ul>
  </li>
  <li>int distanceTo(Location loc)
      <ul>
          <li>returns the distance from the current location to the given location</li>
      </ul>
  </li>
</ul>

