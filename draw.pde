void draw(){
  
  
  
  if(start==1){//home screen
    noStroke();
    if(music2<1){player02.loop();music2=music2+1;}
    background(beach);
     image(snow,100,0,1000,700); 
    image(snow,1000,400,900,600); 

    image(cover,0,0,1800,1000);
    image(grass1,100,600,400,400);
    image(grass1,1350,650,350,350);
    image(grass2,300,850,400,200);
    image(grass2,1200,850,400,200);
    image(menu,2,10,100,100);
    //image(snow,0,0,1800,1000);
        snow.play();

    grass1.play();
    grass2.play();
    textSize(30);
    fill(250);
    text("Ver. 1.0.0",1620,30);
    
    
    for(int i = 0;i<num-1;i++){
     mtheta[i] += dtheta[i];
     v2[i].lerp(mouseX+cos(mtheta[i])*rdt[i], mouseY+sin(mtheta[i])*rdt[i],0,easing[i]);
     fill(c[i]);
     ellipse(v2[i].x, v2[i].y, rdu,rdu);
    }
  }
  
  if(start==2){//settings
    
  background(set);
  image(white,0,200,1800,450);
  image(count2,250,-30,700,200);
  image(done,400,700,400,200);
  image(cancel,1000,700,400,200);
  image(increase,1300,250,50,50);
  image(increase,1300,350,50,50);
  image(increase,1300,450,50,50);
  image(increase,1300,550,50,50);
  image(decrease,1030,250,50,50);
  image(decrease,1030,350,50,50);
  image(decrease,1030,450,50,50);
  image(decrease,1030,550,50,50);
  image(home,0,0,50,50);
  player01.pause();
  player02.pause();
  music1=0;
  music2=0;
 
  fill(250); 
  
  textSize(90);
  text("SETTINGS",390,110);
  fill(10,95,200);
  textSize(50);
  text("Ball Mass",300,300);
  textSize(50);
  text("Damping Coefficient",300,400);  
  textSize(50);
  text("Gravitational Acceleration",300,500); 
  textSize(50);
  text("Force Wind",300,600); 
  
  textSize(50);
  text(-gravity,1100,500); 
  textSize(50);
  text(damping,1100,400); 
  textSize(50);
  text(m,1100,300); 
  textSize(50);
  text(abs(Fw),1100,600);
  
  textSize(50);
  text("kg",1400,300);
  textSize(50);
  text("kg/s",1400,400);  
  textSize(50);
  text("m/s^2",1400,500); 
  textSize(50);
  text("N",1400,600); 
  if(sure==1){start=0; sure=0;starti=start;}
    
  if(sure==2){start=0; sure=0;gravity=gi;m=mi;damping=di;Fw=Fwi;starti=start;}
  
  }
  
  
    if(start==0){//playing screen
    player02.pause();
    stroke(10);
    stroke(255,255,255);
    if(music1<1){player01.loop();music1=music1+1;}
    
    background(beach);
    strokeWeight(5);
    stroke(100);
    line(0,855,100,855);
    line(2,855,2,755);
    textSize(20);
    fill(100);
    text("X",105,860);
    text("Y",5,750);
    textSize(40);
    fill(50);
    text("-Time-",780,130);
    textSize(40);
    fill(50);
    text(time,780,180);

    if(Fw>0){
      image(Wind,820,250,70,70);   
      image(snow,100,0,1000,700); 
    image(snow,1000,400,900,600); 

  }
    if(Fw<0){
    image(Wind2,820,250,70,70);
   image(snow2,700,0,1000,700); 
    image(snow2,-100,400,900,600); 
 
}
    
  
    if(i!=0){//not repeat douch ball time
      i=i+1;
      if(i==20){i=0;}
    }
  
   if(j!=0){//not repeat douch ball time
      j=j+1;
      if(j==20){j=0;}
    }
  
   if(v!=0){//again wait time 
      v=v+1;
      if(v==100){
      v=0;
      if(ballxx<width/2-200){
      ballx=100;
      bally=100;
      ballvx=0;
      ballvy=0;
      }
      if(ballxx>width/2-100){
      ballx=1400;
      bally=100;
      ballvx=0;
      ballvy=0;
      }
    }
  } 
  image(shadow,player1x+10,830,160,50);
  image(shadow,player2x+10,830,160,50);
  image(shadow,ballx+30,830,140,50);
  image(net,740,520,320,480);
  image(blue,760,460,220,220);
  image(fire,805,505,120,120);
  
  player1xi=player1x;
  player2xi=player2x;
  
  image(yellow,player1x-115,player1y-325,480,500);
  image(yellow,player2x-115,player2y-325,480,500);
  
  if(right1==1 && left1==0){player1x=player1x+20;}
  if(left1==1 && right1==0){player1x=player1x-20;}
  if(right2==1 && left2==0){player2x=player2x+20;}
  if(left2==1  && right2==0){player2x=player2x-20;}
   
    if(player1y<830){image(P1jump,player1x,player1y-200,280,280);}
  if(player1x>player1xi && player1y>=830){image(player1,player1x-40,player1y-270,300,300);}
  if(player1x<player1xi && player1y>=830){image(player2,player1x-40,player1y-270,300,300);}
  if(player1x==player1xi && player1y>=830){image(P1s,player1x-45,player1y-285,280,315);}
  
  
  if(player2y<830){image(P2jump,player2x,player2y-200,280,280);}
  if(player2x>player2xi && player2y>=830){image(player1,player2x-40,player2y-270,300,300);}
  if(player2x<player2xi && player2y>=830){image(player2,player2x-40,player2y-270,300,300);}
  if(player2x==player2xi && player2y>=830){image(P2s,player2x-45,player2y-285,280,315);}
  


  
  if(k==0){
  if(player1v!=0 && player1y<=830){player1y+=player1v;player1v+=1.96;}
  if(player2v!=0 && player2y<=830){player2y+=player2v;player2v+=1.96;} 
  }
    if(player1y>830){player1y=830;}
    if(player2y>830){player2y=830;}
    if(player1x<0){player1x=0;}
    if(player1x>650){player1x=650;}
    if(player2x>1600){player2x=1600;}
    if(player2x<850){player2x=850;}
  
    if(k==0){Pendulum.update(); time = time+ft;} 
    
        Pendulum.display(); 
    
    image(shadow,800,920,160,50);
    image(net2,740,520,320,480);
    
    image(front,0,0,1800,1000);  
    image(grass1,100,600,400,400);
    image(grass1,1350,650,350,350);
    image(grass2,300,850,400,200);
    image(grass2,1200,850,400,200);
    image(count1,30,50,200,175);
    image(count2,1570,50,200,175);
    image(blue,630,500,520,520);
    image(fire,715,490,320,320);
    if(k==1&&p==1){
    F1=sqrt(sq(Fx1)+sq(Fy1));
    cos1=Fx1/F1;
    sin1=Fy1/F1;

    trix1=ballx+100+dirx1+trilength*sin(-angle1);
    triy1=bally+70+diry1+trilength*cos(-angle1);
    trix2=ballx+100+dirx1-trilength*sin(-angle1);
    triy2=bally+70+diry1-trilength*cos(-angle1);
    trix3=ballx+100+dirx1+3*trilength*cos(-angle1);
    triy3=bally+70+diry1-3*trilength*sin(-angle1);
    
   
    
    strokeWeight(line1+10);
    stroke(250);
    line(ballx+100,bally+70,ballx+100+dirx1,bally+70+diry1);
    triangle(trix1,triy1,trix2,triy2,trix3,triy3);
    
    strokeWeight(line1);
    stroke(50);
    line(ballx+100,bally+70,ballx+100+dirx1,bally+70+diry1);
    triangle(trix1,triy1,trix2,triy2,trix3,triy3);
    
     //fill(250);
    //noStroke();
    //rect(ballx+25,bally+40,120,50);
    image(count2,ballx+25,bally+40,120,50);
    textSize(30);
    fill(250);
    text(int(F1),ballx+40,bally+80);
    text("N",ballx+110,bally+80);
    
    Length=sqrt(sq(dirx1)+sq(diry1));
    
      if(right1==2){
      F1=F1+5;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1+=0.1;
      if(F1>400){
       F1=400;
       Fx1=F1*cos1;
       Fy1=F1*sin1;
       line1=10+2500*0.1/50;
     }
    }
      if(left1==2){
      F1=F1-5;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1-=0.1;
      if(F1<10){
      F1=10;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1=10-1400*0.1/50;
     }
    }
    if(up1==2){
      angle1=angle1-PI/90;
      if(angle1>2*PI){angle1=angle1-2*PI;}
      if(angle1<0){angle1=angle1+2*PI;}
      Fx1=F1*cos(angle1);
      Fy1=F1*sin(angle1);
      dirx1=Length*cos(angle1);
      diry1=Length*sin(angle1);
    }
    if(down1==2){
      angle1=angle1+PI/90;
      if(angle1>2*PI){angle1=angle1-2*PI;}
      if(angle1<0){angle1=angle1+2*PI;}
      Fx1=F1*cos(angle1);
      Fy1=F1*sin(angle1);
      dirx1=Length*cos(angle1);
      diry1=Length*sin(angle1);
    }
    
    Fx=Fx1;
    Fy=Fy1;
  }
  
  if(k==1&&p==2){
    F2=sqrt(sq(Fx2)+sq(Fy2));
    cos2=Fx2/F2;
    sin2=Fy2/F2;
    
    trix4=ballx+100+dirx2+trilength*sin(-angle2);
    triy4=bally+70+diry2+trilength*cos(-angle2);
    trix5=ballx+100+dirx2-trilength*sin(-angle2);
    triy5=bally+70+diry2-trilength*cos(-angle2);
    trix6=ballx+100+dirx2-3*trilength*cos(-angle2);
    triy6=bally+70+diry2+3*trilength*sin(-angle2);
    
    strokeWeight(line2+10);
    stroke(250);
    line(ballx+100,bally+70,ballx+100+dirx2,bally+70+diry2);
    triangle(trix4,triy4,trix5,triy5,trix6,triy6);
    
    strokeWeight(line2);
    stroke(50);
    line(ballx+100,bally+70,ballx+100+dirx2,bally+70+diry2);
    triangle(trix4,triy4,trix5,triy5,trix6,triy6);
    
   // fill(250);
    //noStroke();
    //rect(ballx+25,bally+40,120,50);
    image(count2,ballx+25,bally+40,120,50);
    textSize(30);
    fill(250);
    text(int(F2),ballx+40,bally+80);
    text("N",ballx+110,bally+80);
    
    Length2=sqrt(sq(dirx2)+sq(diry2));
    
    if(right2==2){
      cos2=Fx2/F2;
      sin2=Fy2/F2;
      F2=F2+5;
      Fx2=F2*cos2;
      Fy2=F2*sin2;
      line2+=0.1;
      if(F2>400){
       F2=400;
       Fx2=F2*cos2;
       Fy2=F2*sin2;
       line2=10+2500*0.1/50;
     }
    }
     if(left2==2){
      cos2=Fx2/F2;
      sin2=Fy2/F2;
      F2=F2-5;
      Fx2=F2*cos2;
      Fy2=F2*sin2;
      line2-=0.1;
       if(F2<10){
       F2=10;
       Fx2=F2*cos2;
       Fy2=F2*sin2;
       line2=10-1400*0.1/50;
     }
    }
    if(up2==2){
      angle2=angle2+PI/90;
      if(angle2>2*PI){angle2=angle2-2*PI;}
      if(angle2<0){angle2=angle2+2*PI;}
      Fx2=-F2*cos(angle2);
      Fy2=-F2*sin(angle2);
      dirx2=-Length2*cos(angle2);
      diry2=-Length2*sin(angle2);
    }
    if(down2==2){
      angle2=angle2-PI/90;
      if(angle2>2*PI){angle2=angle2-2*PI;}
      if(angle2<0){angle2=angle2+2*PI;}
      Fx2=-F2*cos(angle2);
      Fy2=-F2*sin(angle2);
      dirx2=-Length2*cos(angle2);
      diry2=-Length2*sin(angle2);
    }

    Fx=Fx2;
    Fy=Fy2;
  } 



    
    textSize(65);
    fill(250);
    text(score1,95,190);
    textSize(65);
    fill(250);
    text(score2,1640,190); 
    
    textSize(35);
    fill(250);
    text("score",80,130);
    text("score",1625,130);
  
    image(begin,0,0,1800,80);
    textSize(20);
    fill(0);
    text("position_x",20,30);
    textSize(20);
    fill(0);
    text("position_y",20,70);
    textSize(20);
    fill(0);
    text((ballx+100)/100,140,30);
    textSize(20);
    fill(0);
    text((730-bally)/100,140,70);
    textSize(20);
    fill(0);
    text("m",220,70);
    textSize(20);
    fill(0);
    text("m",220,30);
  
    textSize(20);
    fill(0);
    text("velocity_x",300,30);
    textSize(20);
    fill(0);
    text("velocity_y",300,70);
    textSize(20);
    fill(0);
    text(ballvx,420,30);
    textSize(20);
    fill(0);
    text(-ballvy,420,70);
    textSize(20);
    fill(0);
    text("m/s",500,70);
    textSize(20);
    fill(0);
    text("m/s",500,30);
    
    textSize(20);
    fill(0);
    text("acceleration_x",600,30);
    textSize(20);
    fill(0);
    text("acceleration_y",600,70);
    textSize(20);
    fill(0);
    text(ballax,770,30);
    textSize(20);
    fill(0);
    text(-ballay,770,70);
    textSize(20);
    fill(0);
    text("m/s^2",850,70);
    textSize(20);
    fill(0);
    text("m/s^2",850,30);
    
    textSize(20);
    fill(0);
    text("damping coefficient",1000,30);
    textSize(20);
    fill(0);
    text("gravitational acceleration",1000,70);
    textSize(20);
    fill(0);
    text(damping,1270,30);
    textSize(20);
    fill(0);
    text(-gravity,1270,70);
    textSize(20);
    fill(0);
    text("kg/s",1370,30);
    textSize(20);
    fill(0);
    text("m/s^2",1370,70);
  
    textSize(20);
    fill(0);
    text("ball mass",1500,30);
    textSize(20);
    fill(0);
    text("force wind",1500,70);
    textSize(20);
    fill(0);
    text(m,1640,30);
    textSize(20);
    fill(0);
    text(Fw,1640,70);
    textSize(20);
    fill(0);
    text("kg",1720,30);
    textSize(20);
    fill(0);
    text("N",1720,70);
    
    strokeWeight(10);
    stroke(50);
    //line(0,70,1800,70);
   // line(0,125,1800,125);
    line(0,5,1800,5);
    line(0,0,0,80);
    line(1800,0,1800,80);
    line(260,0,260,80);
    line(560,0,560,80);
    line(960,0,960,80);
    line(1460,0,1460,80);
    
}
}

