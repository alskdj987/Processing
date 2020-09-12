void keyPressed(){

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
