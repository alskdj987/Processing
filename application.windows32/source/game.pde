
import ddf.minim.spi.*;
import ddf.minim.signals.*;
import ddf.minim.*;
import ddf.minim.analysis.*;
import ddf.minim.ugens.*;
import ddf.minim.effects.*;
import gifAnimation.*;

int num = 1000;//the number of point(s).
int rdtr = 15;//range of the rdt
int rdu = 5;//radius of circle
//**********
PVector v2[]=new PVector[num];

color c[] = new color[num];//color of each point.
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
float gravity = -9.8,gi; 
float Fx , Fy,Fx1=sqrt(sq(150)/2),Fy1=-sqrt(sq(150)/2),Fx2=-sqrt(sq(150)/2),Fy2=-sqrt(sq(150)/2),F1,F2,sin1,cos1,sin2,cos2,k=0,i=0,j=0,s=0,v=0,p=0;//k:touch ball,p:who touch ball
float Fw=0,Fwi;
float ft=0.034,disx,disy;
float dirx1=100,dirx2=-100,diry1=-100,diry2=-100;
int score1=0,score2=0;
int up1=0,down1=0,left1=0,right1=0;
int up2=0,down2=0,left2=0,right2=0;
float line1=10,line2=10,angle1=atan(Fy1/Fx1),angle2=atan(Fy2/Fx2),Length,Length2,trilength=10;
float trix1,triy1,trix2,triy2,trix3,triy3,trix4,triy4,trix5,triy5,trix6,triy6;
float K1x,K2x,K3x,K4x,D1x,D2x,D3x,D4x,K1y,K2y,K3y,K4y,D1y,D2y,D3y,D4y;
void setup(){

  minim = new Minim(this);//bgm
  player01 = minim.loadFile("003.mp3", 2048);
  player02 = minim.loadFile("002.mp3", 2048);
  
  
  
  for(int i =0;i<num-1;i++){
    c[i] = color(random(150,250),random(200,250),random(150,250));
    v2[i] = new PVector(random(width),random(height));
    theta[i] = round(random(360));

    mtheta[i] = theta[i]/180*PI;
    rdt[i] = round(random(-rdtr,rdtr));
    easing[i] = random(0.02,0.3);
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

void keyReleased(){
  if(keyCode==UP){up2=0;}
  if(keyCode==RIGHT){right2=0;player2vx=0;}
  if(keyCode==DOWN){down2=0;}
  if(keyCode==LEFT){left2=0;player2vx=0;}
  if(keyCode=='W'){up1=0; }
  if(keyCode=='D'){right1=0;player1vx=0;}
  if(keyCode=='S'){down1=0;}
  if(keyCode=='A'){left1=0;player1vx=0;}
}
