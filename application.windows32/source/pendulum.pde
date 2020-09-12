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
  void update() {
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
       ballvx = ballvx * -0.5;
       ballvy = ballvy * -0.5; 
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
    ballvy = ballvy * -0.5; 
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
  void display() {
     location = new PVector(ballx, bally); 
     //image(red,location.x-100, location.y-100, 400, 400);  
    image(ball,location.x, location.y, 175, 143);
   
  }
}
