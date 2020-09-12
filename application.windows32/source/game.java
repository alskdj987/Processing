import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import ddf.minim.spi.*; 
import ddf.minim.signals.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.ugens.*; 
import ddf.minim.effects.*; 
import gifAnimation.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class game extends PApplet {










int num = 1000;//the number of point(s).
int rdtr = 15;//range of the rdt
int rdu = 5;//radius of circle
//**********
PVector v2[]=new PVector[num];

int c[] = new int[num];//color of each point.
float theta[] = new float[num];//original angle of each point.
float mtheta[] = new float[num];//translate angle to math value.
float dtheta[] = new float[num];//speed of theta.
float easing[] = new float[num];
int rdt[] = new int[num];//make a shuffle of radius.

AudioPlayer player01,player02;
AudioPlayer sound;
Minim minim;
PImage beach,net,net2,shadow,begin,done,cancel,increase,decrease,home,menu,Wind,Wind2,front,front2,count1,count2,blue,yellow,set,white,cover; 
Gif ball,player1,player2,P1jump,P2jump,Start,snow,snow2,P1s,P2s,fire,grass1,grass2;
Pendulum Pendulum;
float time;
float hit=0;
float start=1,starti;
float music1=0,music2=0;
float sure=0;
float mousex,mousey;
PVector location;  
float player1x=100,player1y=830,player1v=0,player1vx=10,player1xi; 
float player2x=1500,player2y=830,player2v=0,player2vx=-10,player2xi; 
float damping,di;
float ballx,bally,ballxx;
float ballvx,ballvy;
float ballax,ballay;
float m=1,mi;
float wd=0,w;
float gravity = -9.8f,gi; 
float Fx , Fy,Fx1=sqrt(sq(150)/2),Fy1=-sqrt(sq(150)/2),Fx2=-sqrt(sq(150)/2),Fy2=-sqrt(sq(150)/2),F1,F2,sin1,cos1,sin2,cos2,k=0,i=0,j=0,s=0,v=0,p=0;//k:touch ball,p:who touch ball
float Fw=0,Fwi;
float ft=0.034f,disx,disy;
float dirx1=100,dirx2=-100,diry1=-100,diry2=-100;
int score1=0,score2=0;
int up1=0,down1=0,left1=0,right1=0;
int up2=0,down2=0,left2=0,right2=0;
float line1=10,line2=10,angle1=atan(Fy1/Fx1),angle2=atan(Fy2/Fx2),Length,Length2,trilength=10;
float trix1,triy1,trix2,triy2,trix3,triy3,trix4,triy4,trix5,triy5,trix6,triy6;
float K1x,K2x,K3x,K4x,D1x,D2x,D3x,D4x,K1y,K2y,K3y,K4y,D1y,D2y,D3y,D4y;
public void setup(){

  minim = new Minim(this);//bgm
  player01 = minim.loadFile("003.mp3", 2048);
  player02 = minim.loadFile("002.mp3", 2048);
  
  
  
  for(int i =0;i<num-1;i++){
    c[i] = color(random(150,250),random(200,250),random(150,250));
    v2[i] = new PVector(random(width),random(height));
    theta[i] = round(random(360));

    mtheta[i] = theta[i]/180*PI;
    rdt[i] = round(random(-rdtr,rdtr));
    easing[i] = random(0.02f,0.3f);
  }
  
  size(1800,1000);
  set = loadImage("beach4.jpg");
  beach = loadImage("beach3.jpg");
  begin = loadImage("begin.jpg");
  net = loadImage("net.png");
  net2 = loadImage("net2.png");
  blue = loadImage("blue.png");
  white = loadImage("white.png");
  yellow = loadImage("yellow.png");
 cover = loadImage("cover.png");
  shadow = loadImage("shadow.png");
  done = loadImage("done.jpg");
  cancel = loadImage("cancel.jpg");
  increase = loadImage("increase.jpg");
  decrease = loadImage("decrease.jpg");
  home = loadImage("home.jpg");
  
  menu = loadImage("menu.png");
  Wind = loadImage("wind.jpg");
  Wind2 = loadImage("wind2.jpg");
  front = loadImage("front3.png");
  count1 = loadImage("count1.png");
  count2 = loadImage("count2.png");
  ball=new Gif(this,"Ball.gif");
  player1=new Gif(this,"A.gif");
  player2=new Gif(this,"A2.gif");
  P1jump=new Gif(this,"B2.gif");
  P2jump=new Gif(this,"B.gif");
  P1s=new Gif(this,"C.gif");
  P2s=new Gif(this,"C2.gif");
  Start=new Gif(this,"start.gif");
  snow=new Gif(this,"snow.gif");
  snow2=new Gif(this,"snow2.gif");
  fire=new Gif(this,"fire.gif");
  grass1=new Gif(this,"grass1.gif");
  grass2=new Gif(this,"grass2.gif");
  ball.play();
  
  player1.loop();
  player2.loop();
  P1jump.loop();
  P2jump.loop();
  P1s.loop();
  P2s.loop();
  Start.loop();
  snow.loop();
  snow2.loop();
  fire.loop();
  grass1.loop();
  grass2.loop();
 
  Pendulum = new Pendulum(100,100,0,0,0,0,0); 
}

public void keyReleased(){
  if(keyCode==UP){up2=0;}
  if(keyCode==RIGHT){right2=0;player2vx=0;}
  if(keyCode==DOWN){down2=0;}
  if(keyCode==LEFT){left2=0;player2vx=0;}
  if(keyCode=='W'){up1=0; }
  if(keyCode=='D'){right1=0;player1vx=0;}
  if(keyCode=='S'){down1=0;}
  if(keyCode=='A'){left1=0;player1vx=0;}
}
public void keyPressed(){

  if(k==1 && p==1){if(keyCode=='F'){
     i=1;  
     ballvx=0;
     ballvy=0; 
     ballax=-(damping/m)*ballvx+Fx/m+Fw/m;
     ballay=-gravity-(damping/m)*ballvy+Fy/m;
   
     K1x = ft*ballvx;
     D1x = ft*ballax;
     K2x = ft*(ballvx+D1x/2);
     D2x = ft*(-(damping/m)*(ballvx+D1x/2)+Fx/m+Fw/m);
     K3x = ft*(ballvx+D2x/2);
     D3x = ft*(-(damping/m)*(ballvx+D2x/2)+Fx/m+Fw/m); 
     K4x = ft*(ballvx+D3x);
     D4x = ft*(-(damping/m)*(ballvx+D3x)+Fx/m+Fw/m); 
     
     K1y = ft*ballvy;
     D1y = ft*ballay;
     K2y = ft*(ballvy+D1y/2);
     D2y = ft*(-gravity-(damping/m)*(ballvy+D1y/2)+Fy/m);
     K3y = ft*(ballvy+D2y/2);
     D3y = ft*(-gravity-(damping/m)*(ballvy+D2y/2)+Fy/m); 
     K4y = ft*(ballvy+D3y);
     D4y = ft*(-gravity-(damping/m)*(ballvy+D3y)+Fy/m); 
     
     ballvx = ballvx+(D1x+2*D2x+2*D3x+D4x)/6;
     ballvy = ballvy+(D1y+2*D2y+2*D3y+D4y)/6;
     
     ballx = ballx+100*(K1x+2*K2x+2*K3x+K4x)/6;
     bally = bally+100*(K1y+2*K2y+2*K3y+K4y)/6;
   
     dirx1=100;
     diry1=-100;
     Fx1=sqrt(sq(150)/2);
     Fy1=-sqrt(sq(150)/2);
     line1=10;
     angle1=atan(Fy1/Fx1);
     k=0;
     hit=1;
    player1.play();
    player2.play();
    P1jump.play();
    P2jump.play();
    P1s.play();
    P2s.play();
    ball.play();
    snow.play();
    snow2.play();
    fire.play();
    grass1.play();
    grass2.play();
     }
   }
  
  if(k==1 && p==2){if(keyCode==ENTER){
     j=1;
     ballvx=0;
     ballvy=0; 
     ballax=-(damping/m)*ballvx+Fx/m+Fw/m;
     ballay=-gravity-(damping/m)*ballvy+Fy/m;
   
     K1x = ft*ballvx;
     D1x = ft*ballax;
     K2x = ft*(ballvx+D1x/2);
     D2x = ft*(-(damping/m)*(ballvx+D1x/2)+Fx/m+Fw/m);
     K3x = ft*(ballvx+D2x/2);
     D3x = ft*(-(damping/m)*(ballvx+D2x/2)+Fx/m+Fw/m); 
     K4x = ft*(ballvx+D3x);
     D4x = ft*(-(damping/m)*(ballvx+D3x)+Fx/m+Fw/m); 
     
     K1y = ft*ballvy;
     D1y = ft*ballay;
     K2y = ft*(ballvy+D1y/2);
     D2y = ft*(-gravity-(damping/m)*(ballvy+D1y/2)+Fy/m);
     K3y = ft*(ballvy+D2y/2);
     D3y = ft*(-gravity-(damping/m)*(ballvy+D2y/2)+Fy/m); 
     K4y = ft*(ballvy+D3y);
     D4y = ft*(-gravity-(damping/m)*(ballvy+D3y)+Fy/m); 
     
     ballvx = ballvx+(D1x+2*D2x+2*D3x+D4x)/6;
     ballvy = ballvy+(D1y+2*D2y+2*D3y+D4y)/6;
     
     ballx = ballx+100*(K1x+2*K2x+2*K3x+K4x)/6;
     bally = bally+100*(K1y+2*K2y+2*K3y+K4y)/6;
   
     dirx2=-100;
     diry2=-100;
     Fx2=-sqrt(sq(150)/2);
     Fy2=-sqrt(sq(150)/2);
     line2=10;
     angle2=atan(Fy2/Fx2);
     k=0;
     hit=1;
     player1.play();
     player2.play();
     P1jump.play();
     P2jump.play();
     P1s.play();
    P2s.play();
     ball.play();
     snow.play();
    snow2.play();
    fire.play();
    grass1.play();
    grass2.play();
  }}
  
  if(keyCode==DELETE){start=2;mi=m;di=damping;gi=gravity;Fwi=Fw;}
  
  
  if(k==0){
    if(keyCode==UP){
    if(player2y==830){player2v=-40;}
    }
  }
  if(k==1){if(keyCode==UP){up2=2;}}
  
  if(k==1){if(keyCode==DOWN){down2=2;}}
  
  if(k==0){if(keyCode==RIGHT){right2=1;player2vx=10;}}
  if(k==1){if(keyCode==RIGHT){right2=2;}}
  
  
  if(k==0){if(keyCode==LEFT){left2=1;;player2vx=-10;}}
  if(k==1){if(keyCode==LEFT){left2=2;}}
  
  
  if(k==0){
  if(keyCode=='W'){
    if(player1y==830){player1v=-40;}
    }
  }
  
  if(k==1){if(keyCode=='W'){up1=2;}}
  
  if(k==1){if(keyCode=='S'){down1=2;}}
  
  if(k==0){if(keyCode=='D'){right1=1;;player1vx=10;}}
  if(k==1){if(keyCode=='D'){right1=2;}}
  
  if(k==0){if(keyCode=='A'){left1=1;;player1vx=-10;}}
  if(k==1){if(keyCode=='A'){left1=2;}}
}
public void draw(){
  
  
  
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
  if(player1v!=0 && player1y<=830){player1y+=player1v;player1v+=1.96f;}
  if(player2v!=0 && player2y<=830){player2y+=player2v;player2v+=1.96f;} 
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
    text(PApplet.parseInt(F1),ballx+40,bally+80);
    text("N",ballx+110,bally+80);
    
    Length=sqrt(sq(dirx1)+sq(diry1));
    
      if(right1==2){
      F1=F1+5;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1+=0.1f;
      if(F1>400){
       F1=400;
       Fx1=F1*cos1;
       Fy1=F1*sin1;
       line1=10+2500*0.1f/50;
     }
    }
      if(left1==2){
      F1=F1-5;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1-=0.1f;
      if(F1<10){
      F1=10;
      Fx1=F1*cos1;
      Fy1=F1*sin1;
      line1=10-1400*0.1f/50;
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
    text(PApplet.parseInt(F2),ballx+40,bally+80);
    text("N",ballx+110,bally+80);
    
    Length2=sqrt(sq(dirx2)+sq(diry2));
    
    if(right2==2){
      cos2=Fx2/F2;
      sin2=Fy2/F2;
      F2=F2+5;
      Fx2=F2*cos2;
      Fy2=F2*sin2;
      line2+=0.1f;
      if(F2>400){
       F2=400;
       Fx2=F2*cos2;
       Fy2=F2*sin2;
       line2=10+2500*0.1f/50;
     }
    }
     if(left2==2){
      cos2=Fx2/F2;
      sin2=Fy2/F2;
      F2=F2-5;
      Fx2=F2*cos2;
      Fy2=F2*sin2;
      line2-=0.1f;
       if(F2<10){
       F2=10;
       Fx2=F2*cos2;
       Fy2=F2*sin2;
       line2=10-1400*0.1f/50;
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

public void mousePressed(){
    if(start==1){
      if(mouseX>420 && mouseX<1150){
      if(mouseY>600 && mouseY<1000){start=0;starti=start;}
      }
      if(mouseX>0 && mouseX<100){
      if(mouseY>0 && mouseY<100){start=2;starti=1;}
      }
    }
    if(start==2){
      if(mouseX>0 && mouseX<50){
         if(mouseY>0 && mouseY<50){start=1;starti=start;m=1;gravity=-9.8f;damping=0;ballvx=0;ballvy=0;ballax=0;ballay=0;ballx=100;bally=100;player1x=100;player1y=830;player1v=0;player2x=1500;player2y=830;player2v=0;score1=0;score2=0;k=0;Fw=0;p=0;player1vx=10;player2vx=-10;time=0;}
      }
      
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>250 && mouseY<300){m=m+0.1f;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>250 && mouseY<300){
           m=m-0.1f;
           if(m<0.1f){m=0.1f;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>350 && mouseY<400){damping=damping+0.01f;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>350 && mouseY<400){
           damping=damping-0.01f;
           if(damping<0){damping=0;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>450 && mouseY<500){gravity=gravity-0.1f;}
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>450 && mouseY<500){
           gravity=gravity+0.1f;
           if(-gravity<0.1f){gravity=-0.1f;}
        }
      }
      if(mouseX>1300 && mouseX<1350){
         if(mouseY>550 && mouseY<600){
         int minus=0;
         if(Fw<0){
         minus=-1;}
         Fw=abs(Fw)+0.1f;
         if(minus==-1){Fw=-Fw;}
       }
      }
      if(mouseX>1030 && mouseX<1080){
         if(mouseY>550 && mouseY<600){
           int minus=0;
           if(Fw<0){minus=-1;}
           Fw=abs(Fw)-0.1f;
           if(Fw<0.1f){Fw=0;}
           if(minus==-1){Fw=-Fw;if(Fw==-1*0){Fw=0;}}
        }
      }
      if(mouseX>400 && mouseX<800){
         if(mouseY>700 && mouseY<900){
           if(starti==0){sure=1;}
           if(starti==1){start=1;starti=start;}
       }
      }
      if(mouseX>1000 && mouseX<1400){
         if(mouseY>700 && mouseY<900){
           if(starti==0){sure=2;}
           if(starti==1){start=1;starti=start;}
       }
      }
    }
}
class Pendulum {

  Pendulum(float ballx_,float bally_,float d,float ballvx_,float ballvy_,float ballax_,float ballay_) {
    location = new PVector(0, 0); 
    ballx=ballx_;
    bally=bally_;
    ballvx=ballvx_;
    ballvy=ballvy_;
    ballax= ballax_;
    ballay= ballay_;
    damping = d;
  }
  public void update() {
    if(wd==0){w=random(500,1000);}
    wd=wd+1;
    if(wd>w){Fw=-Fw;wd=0;if(Fw==-0){Fw=0;}}
     
    if(k==0){
      if(ballx<width/2-200){s=1;}
      if(ballx>width/2-100){s=2;}
      if(hit==0){
     ballax=(-(damping/m)*ballvx+Fw/m);
     ballay=(-gravity-(damping/m)*ballvy);
   
     K1x = ft*ballvx;
     D1x = ft*ballax;
     K2x = ft*(ballvx+D1x/2);
     D2x = ft*(-(damping/m)*(ballvx+D1x/2)+Fw/m);
     K3x = ft*(ballvx+D2x/2);
     D3x = ft*(-(damping/m)*(ballvx+D2x/2)+Fw/m); 
     K4x = ft*(ballvx+D3x);
     D4x = ft*(-(damping/m)*(ballvx+D3x)+Fw/m); 
     
     K1y = ft*ballvy;
     D1y = ft*ballay;
     K2y = ft*(ballvy+D1y/2);
     D2y = ft*(-gravity-(damping/m)*(ballvy+D1y/2));
     K3y = ft*(ballvy+D2y/2);
     D3y = ft*(-gravity-(damping/m)*(ballvy+D2y/2)); 
     K4y = ft*(ballvy+D3y);
     D4y = ft*(-gravity-(damping/m)*(ballvy+D3y)); 
      }
     
     if(hit>0){
     ballax=-(damping/m)*ballvx+Fx/m+Fw/m;
     ballay=-gravity-(damping/m)*ballvy+Fy/m;
   
     K1x = ft*ballvx;
     D1x = ft*ballax;
     K2x = ft*(ballvx+D1x/2);
     D2x = ft*(-(damping/m)*(ballvx+D1x/2)+Fx/m+Fw/m);
     K3x = ft*(ballvx+D2x/2);
     D3x = ft*(-(damping/m)*(ballvx+D2x/2)+Fx/m+Fw/m); 
     K4x = ft*(ballvx+D3x);
     D4x = ft*(-(damping/m)*(ballvx+D3x)+Fx/m+Fw/m); 
     
     K1y = ft*ballvy;
     D1y = ft*ballay;
     K2y = ft*(ballvy+D1y/2);
     D2y = ft*(-gravity-(damping/m)*(ballvy+D1y/2)+Fy/m);
     K3y = ft*(ballvy+D2y/2);
     D3y = ft*(-gravity-(damping/m)*(ballvy+D2y/2)+Fy/m); 
     K4y = ft*(ballvy+D3y);
     D4y = ft*(-gravity-(damping/m)*(ballvy+D3y)+Fy/m); 
     hit = hit+1;
     if(hit==2){hit=0;}
     }
     
     ballvx = ballvx+(D1x+2*D2x+2*D3x+D4x)/6;
     ballvy = ballvy+(D1y+2*D2y+2*D3y+D4y)/6;
     
     ballx = (ballx+100*(K1x+2*K2x+2*K3x+K4x)/6);
     bally = (bally+100*(K1y+2*K2y+2*K3y+K4y)/6);
     
    }
   if(bally>height/2){
     if ((ballx> width/2-200) && (ballx< width/2-100)){
       if((ballx> width/2-200) && (ballx< width/2-100)&&s==1){ballx=width/2-200;}
       if((ballx> width/2-200) && (ballx< width/2-100)&&s==2){ballx=width/2-100;}
       ballvx = ballvx * -0.5f;
       ballvy = ballvy * -0.5f; 
     }
   } 
    
    
   if ((ballx> width-100)) {
    ballvx = ballvx * -1;
    ballx= width-100;
  }
  
   if (ballx < 0-100) {
    ballvx = ballvx * -1;
    ballx = 0-100;
  }
  
  
  
  if (bally > height-270) {
    // We're reducing velocity ever so slightly 
    // when it hits the bottom of the window
    if(ballx<width/2-200 && v==0){score2=score2+1;v=1;ballxx=ballx;}
    if(ballx>width/2-100 && v==0){score1=score1+1;v=1;ballxx=ballx;}
    ballvy = ballvy * -0.5f; 
    bally = height-270;
  }
  
  if(i==0&&v==0){
  if (dist(player1x+100,player1y-130,ballx+100,bally+70)<100){
    mousex=mouseX;
    mousey=mouseY;
    P1jump.stop();
    player1.stop();
    player2.stop();
    P2jump.stop();
    P1s.stop();
    P2s.stop();
    ball.stop();
    snow.stop();
    snow2.stop();
    fire.stop();
    grass1.stop();
    grass2.stop();
    right1=0;
    right2=0;
    left1=0;
    left2=0;
    k=1;
    p=1;
  }
  }
  
   if(j==0&&v==0){  
   if (dist(player2x+100,player2y-130,ballx+100,bally+70)<100){
    mousex=mouseX;
    mousey=mouseY;
    P1jump.stop();
    player1.stop();
    player2.stop();
    P2jump.stop();
    P1s.stop();
    P2s.stop();
    ball.stop();
    snow.stop();
    snow2.stop();
    fire.stop();
    grass1.stop();
    grass2.stop();
    right1=0;
    right2=0;
    left1=0;
    left2=0;
    k=1;
    p=2;
  } 
  }

  }
  public void display() {
     location = new PVector(ballx, bally); 
     //image(red,location.x-100, location.y-100, 400, 400);  
    image(ball,location.x, location.y, 175, 143);
   
  }
}
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "game" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
