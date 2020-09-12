# -*- coding: utf-8 -*-
"""
Created on Mon Apr 20 19:46:11 2020
Projet
"""
from vpython import *
import numpy as np
import math
import random as rd
"""
 1. 參數設定, 設定變數及初始值
"""
pi=math.pi
number=0
J=False
controllV=1
controllB=1
controllV1=1
Laser_on=True
B_field_on=True
g=9.8
gray = color.gray(0.7) # color of edges of container
hb=0.05#coil hight(m)
Bs = 0.005 # 雷射光般大小
L = 0.15      # 雷射長度
coilsize=0.2
coil_R=0.02
Natoms =300  # change this to have more or fewer atoms
Ratom=0.0005  # size of a tom
I=6  #current
NB=200 # the nmber of coil
mass=1.44E-25# Rubidium87 mass
K = 1.4E-23  #Boltzmann constant
k=2*pi/780E-9 #wave number
T = 300 # around room temperature
ub=9.274E-24# Bohr Magneton
u0=1E-6
h=1E-34 #Planck's Constant
S=10    # I/I_s
dw=2*pi*13E6 # fr
gama=2*pi*6E6
dt=0.0001
t=0
fuck1=8*(h*(k**2)*dw*S)/(gama*(1+S+(2*dw/gama)**2)**2)
dB_field=(NB*(3*u0*I*((coil_R)**2)*(hb))/((coil_R)**2+(hb)**2)**(2.5))
fuck2=dB_field*(0.5*ub*fuck1/(h*k))
T_MOT=0
pp=0


"""
 2. 畫面設定1(laser&coill&chamber)
"""
scence1 = canvas(title="Laser Cooling", width=1000, height=1000, center=vec(0, 0, 0), background=vec(0, 0.6, 0.6),align='left')
scence1.range = L
gd = graph(title="<i>Number</i>-<i>t</i> plot", width=600, height=450, x=0, y=400,align='left',
               xtitle="<i>t</i> (s)", ytitle="<i>Number</i> ")
Coupling_ON = gcurve(graph=gd, color = color.blue)
gd2 = graph(title="<i>T</i>(K)-<i>t</i> plot", width=600, height=450, x=0, y=400,align='left',
               xtitle="<i>t</i> (s)", ytitle="<i>T</i> ",ymin=0,ymax=1)
TT= gcurve(graph=gd2, color = color.blue)
"""
 3. 畫面設定2(磁力線)
"""
scence2= canvas(title="Magnetic Field", width=600, height=600, center=vec(0, 0, 0),background=vec(0,0,0),forward =vec(0,-1,0.001),align='left')
scence2.range = 6

"""
Laser
"""
B1 = cylinder(pos=vector(-L/2,0,0),axis=vector(L,0,0), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B2 = cylinder(pos=vector(L/2,0,0),axis=vector(-L,0,0), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B3 = cylinder(pos=vector(0,-L/2,0),axis=vector(0,L,0), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B4 = cylinder(pos=vector(0,L/2,0),axis=vector(0,-L,0), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B5 = cylinder(pos=vector(0,0,-L/2),axis=vector(0,0,L), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B6 = cylinder(pos=vector(0,0,L/2),axis=vector(0,0,-L), radius=Bs,opacity=0.25,color=color.red,canvas=scence1)
B7 = cylinder(pos=vector(-L/2,0,L/2),axis=vector(0.8*L,0,-0.8*L), radius=1.1*Bs,opacity=0.2,color=color.green,canvas=scence1)
#O=sphere(pos=vector(0,0,0), radius=0.5*Ratom, color=color.black ,opacity=0.5,canvas=scence1)
#atom=sphere(pos = vec(0,0,0), radius=Ratom,color=color.cyan,v=vec(vx,vy,vz),canvas=scence1)
"""
Coil
"""
for i in range(4):
    coil1=ring(pos=vec(0, 0.06+0.004*i, 0), axis=vec(0, 1, 0), radius=coil_R, thickness=0.25*Bs, color=vector(1,0.7,0.2),canvas=scence1)
for i in range(4):
    coil2=ring(pos=vec(0, -0.06-0.004*i, 0), axis=vec(0, 1, 0), radius=coil_R, thickness=0.25*Bs, color=vector(1,0.7,0.2),canvas=scence1)
"""
chamber
"""
d =0.05+Ratom
r1 = 0.002
boxbottom = curve(color=gray, radius=r1,canvas=scence1)
boxbottom.append([vector(-d,-d,-d), vector(-d,-d,d), vector(d,-d,d), vector(d,-d,-d), vector(-d,-d,-d)])
boxtop = curve(color=gray, radius=r1,canvas=scence1)
boxtop.append([vector(-d,d,-d), vector(-d,d,d), vector(d,d,d), vector(d,d,-d), vector(-d,d,-d)])
vert1 = curve(color=gray, radius=r1,canvas=scence1)
vert2 = curve(color=gray, radius=r1,canvas=scence1)
vert3 = curve(color=gray, radius=r1,canvas=scence1)
vert4 = curve(color=gray, radius=r1,canvas=scence1)
vert1.append([vector(-d,-d,-d), vector(-d,d,-d)])
vert2.append([vector(-d,-d,d), vector(-d,d,d)])
vert3.append([vector(d,-d,d), vector(d,d,d)])
vert4.append([vector(d,-d,-d), vector(d,d,-d)])

"""
Generate Atom
"""
Atoms = []
p = []
apos = []
pavg = sqrt(2*mass*1.5*K*T) # average kinetic energy p**2/(2mass) = (3/2)kT
for i in range(Natoms):
    x = d*rd.uniform(-1, 1)
    y = d*rd.uniform(-1, 1)
    z = d*rd.uniform(-1, 1)
    if i == 0:
        Atoms.append(sphere(pos=vector(x,y,z), radius=Ratom, color=color.blue, make_trail=True, retain=100, trail_radius=0.3*Ratom,canvas=scence1,M=0))
    else: Atoms.append(sphere(pos=vector(x,y,z), radius=Ratom, color=vector(1,0.7,0.2),canvas=scence1,M=0))
    apos.append(vec(x,y,z))
    theta = pi*rd.random()
    phi = 2*pi*rd.random()
    px = pavg*sin(theta)*cos(phi)
    py = pavg*sin(theta)*sin(phi)
    pz = pavg*cos(theta)
    p.append(vector(px,py,pz))


"""
Function &&Botton
"""
def setup():
    global ic,R,Ni,br,bc,r
    ic=6
    R=1
    Ni=40
    br=0.1
    bc=vec(1,0,0)
    arrx=arrow(pos=vec(0,0,0),axis=vec(4,0,0),shaftwidth=0.02,color=color.yellow,canvas=scence2)
    arrY=arrow(pos=vec(0,0,0),axis=vec(0,4,0),shaftwidth=0.02,color=color.green,canvas=scence2)
    arrZ=arrow(pos=vec(0,0,0),axis=vec(0,0,4),shaftwidth=0.04,color=color.blue,canvas=scence2)
    for i in range(8):
        xi=-0.7+0.2*i
        r=vec(xi,0,-2)
        ball=sphere(pos=r,color=vec(0,0,1),radius=0.05,opacity=0,canvas=scence2)
        Bline(bc)
    for i in range(8):
        xi=-0.7+0.2*i
        r=vec(xi,0,2)
        ball=sphere(pos=r,color=vec(0,0,1),radius=0.05,opacity=0,canvas=scence2)
        Bline(bc)

def init():
    global ic,R,Ni,br,bc,r
    ic=6
    R=1.
    Ni=40
    br=0.1
    bc=vec(1,0,0)
    arrx=arrow(pos=vec(0,0,0),axis=vec(4,0,0),shaftwidth=0.02,color=color.yellow,canvas=scence2)
    arrY=arrow(pos=vec(0,0,0),axis=vec(0,4,0),shaftwidth=0.02,color=color.green,canvas=scence2)
    arrZ=arrow(pos=vec(0,0,0),axis=vec(0,0,4),shaftwidth=0.04,color=color.blue,canvas=scence2)





def laser(b1):
    global Laser_on
    Laser_on = not Laser_on
    if  Laser_on:
        B1.opacity=0.25
        B2.opacity=0.25
        B3.opacity=0.25
        B4.opacity=0.25
        B5.opacity=0.25
        B6.opacity=0.25
        B7.opacity=0.25
        b1.text="Laser off"
        print("Laser on")
    else:
        B1.opacity=0
        B2.opacity=0
        B3.opacity=0
        B4.opacity=0
        B5.opacity=0
        B6.opacity=0
        B7.opacity=0
        b1.text="Laser on"
        print("Laser off")
b1 = button(text="Laser off", pos=scence1.title_anchor, bind=laser)

def B_field(b2):
    global B_field_on
    B_field_on = not  B_field_on

    if B_field_on:
        b2.text="B-field off"
        print("B-field on")
    else:
        b2.text="B-field on"
        print("B-field off")
b2 = button(text="B-field off", pos=scence1.title_anchor, bind=B_field)

def Rest(b3):
    number=0
    for i in range(Natoms):
        Atoms[i].color=vector(1,0.7,0.2)
        theta = pi*rd.random()
        phi = 2*pi*rd.random()
        px = pavg*sin(theta)*cos(phi)
        py = pavg*sin(theta)*sin(phi)
        pz = pavg*cos(theta)
        p[i]=vec(px,py,pz)
b3 = button(text="Reset", pos=scence1.title_anchor, bind=Rest)


def checkCollisions():
    hitlist = []
    r2 = 2*Ratom
    r2 *= r2
    for i in range(Natoms):
        ai = apos[i]
        for j in range(i) :
            aj = apos[j]
            dr = ai - aj
            if mag2(dr) < r2 : hitlist.append([i,j])
    return hitlist

def BF_coil_2(curr):
    global R,Ni
    global ic
    global r,B
    global br,bc
    Z2=2.
    mu0=1.
    ds=2.*pi*R/Ni
    B=vec(0,0,0)
    for i in range(Ni):
        t=2.*pi/Ni*i
        rp=vec(R*cos(t),R*sin(t),-2)
        dl=ds*vec(-sin(t),cos(t),0.)
        if(curr==0): arr=arrow(pos=rp, axis=dl,shaftwidth=0.02, headwidth = 0.05,color=color.orange,canvas=scence2)
        rrp=r-rp
        rrp0=mag(rrp)
        dB=mu0*ic/4.*pi*cross(dl,rrp)/rrp0**3
        B=B+dB
    for i in range(Ni):
        t=2.*pi/Ni*i
        rp=vec(R*cos(t),R*sin(t),Z2)
        dl=ds*vec(-sin(t),cos(t),0)
        if(curr==0):    arr=arrow(pos=rp, axis=-dl,shaftwidth=0.02, headwidth = 0.05, color=color.orange,canvas=scence2)
        rrp=r-rp
        rrp0=mag(rrp)
        dB=mu0*ic/4.*pi*cross(-dl,rrp)/rrp0**3
        B=B+dB
       
def Bline(a):
    global r,B
    maxi=2000
    ss=0.01
    ri=r
    rmin=r
    curr=0; term=maxi
    for i in range(maxi):
        BF_coil_2(curr)
        curr=1
        nr=r+B/mag(B)*ss
        arr=sphere(pos=r,axis=nr-r,radius=0.02,color=a,canvas=scence2)
        r=nr
        dr=mag(r-ri)
        if(i==0):   
            dr0=1000. 
            dr=1000.
        else:
            if(i > 100 and dr < dr0):
                dr0=dr
                rmin=r
            if(i>100 and dr < 0.08):  
                term=i
                break
            if(i>100 and dr > 8.):  
                term=i
                break

def Bfield(b4):
    global ic,R,Ni,br,bc,r,J
    J = not J
    if J:
        b4.text="Clear"
        setup()
    else:
        delete(scence2)
    
b4 = button(text="Bfield", pos=scence2.title_anchor, bind=Bfield)



"""
Magneto Optical Trap (MOT)
"""
while True:
    
    rate(500)
    if  Laser_on:controllV=1  
    else: controllV=0    
    if B_field_on:controllB=1     
    else:controllB=0
    if number>=1:
        TT.plot(pos=(t*1E3,T_MOT*1E3))
        pp=pp/number
        T_MOT=pp/(3*number*K*mass)
        pp = 0
    Coupling_ON.plot(pos=(t,number))
  
    number=0
    
    # Update all positions
    for i in range(Natoms):
        if mag(apos[i])<=Bs :
            F_x = -controllV*(fuck1*p[i].x/mass+fuck2*apos[i].x*controllB)
            F_y = -controllV*(fuck1*p[i].y/mass+fuck2*apos[i].y*controllB)-mass*g
            F_z = -controllV*(fuck1*p[i].z/mass+fuck2*apos[i].z*controllB)
            p[i].x += (F_x)*dt
            p[i].y += (F_y)*dt
            p[i].z += (F_z)*dt
            apos[i].x += (p[i].x/mass)*dt
            apos[i].y += (p[i].y/mass)*dt
            apos[i].z += (p[i].z/mass)*dt
            Atoms[i].pos = apos[i]
            Atoms[i].M=1
            if(i!=0 and Laser_on):
                Atoms[i].color=color.cyan
                pp+=mag2(p[i])
                
        else:
            Atoms[i].pos = apos[i] = apos[i] + (p[i]/mass)*dt
            if(i!=0 and Laser_on):Atoms[i].color=vector(1,0.7,0.2)
            if Atoms[i].M==1: Atoms[i].M=0

    
    # Check for collisions
    hitlist = checkCollisions()

    # If any collisions took place, update momenta of the two atoms
    for ij in hitlist:
        i = ij[0]
        j = ij[1]
        ptot = p[i]+p[j]
        posi = apos[i]
        posj = apos[j]
        vi = p[i]/mass
        vj = p[j]/mass
        vrel = vj-vi
        a = vrel.mag2
        if a == 0: continue;  # exactly same velocities
        rrel = posi-posj
        if rrel.mag > Ratom: continue # one atom went all the way through another
    
        # theta is the angle between vrel and rrel:
        dx = dot(rrel, vrel.hat)       # rrel.mag*cos(theta)
        dy = cross(rrel, vrel.hat).mag # rrel.mag*sin(theta)
        # alpha is the angle of the triangle composed of rrel, path of atom j, and a line
        #   from the center of atom i to the center of atom j where atome j hits atom i:
        alpha = asin(dy/(2*Ratom)) 
        d = (2*Ratom)*cos(alpha)-dx # distance traveled into the atom from first contact
        deltat = d/vrel.mag         # time spent moving from first contact to position inside atom
        
        posi = posi-vi*deltat # back up to contact configuration
        posj = posj-vj*deltat
        mtot = 2*mass
        pcmi = p[i]-ptot*mass/mtot # transform momenta to cm frame
        pcmj = p[j]-ptot*mass/mtot
        rrel = norm(rrel)
        pcmi = pcmi-2*pcmi.dot(rrel)*rrel # bounce in cm frame
        pcmj = pcmj-2*pcmj.dot(rrel)*rrel
        p[i] = pcmi+ptot*mass/mtot # transform momenta back to lab frame
        p[j] = pcmj+ptot*mass/mtot
        apos[i] = posi+(p[i]/mass)*deltat # move forward deltat in time
        apos[j] = posj+(p[j]/mass)*deltat

    # When through the trap region and hit the wall
    for i in range(Natoms):
        loc = apos[i]
        number+=Atoms[i].M
        if abs(loc.x) >0.05-Ratom:
            if loc.x < 0: p[i].x =  abs(p[i].x)
            else: p[i].x =  -abs(p[i].x)
        
        if abs(loc.y) >0.05-Ratom:
            if loc.y < 0: p[i].y = abs(p[i].y)
            else: p[i].y =  -abs(p[i].y)
        
        if abs(loc.z) > 0.05-Ratom:
            if loc.z < 0: p[i].z =  abs(p[i].z)
            else: p[i].z =  -abs(p[i].z)
    t+=dt


