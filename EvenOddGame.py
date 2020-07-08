import speech_recognition as sr
from random import randrange

def userChoice():
    r=sr.Recognizer()
    with sr.Microphone() as source:
        print("What do you choose Odd or Even")
        audio=r.listen(source)
        try:
            text=r.recognize_google(audio)
            print(text)
            if text=="even":
                print("Youe choice is even")
                return 1
            else:
                print("Your choice is odd")
                return 0
        except:
            print("Sorry could not hear")
def userBatBall():
    r=sr.Recognizer()
    with sr.Microphone() as source:
        print("Do you want to bat or ball")
        audio=r.listen(source)
        try:
            text=r.recognize_google(audio)
            print(text)
            if text=="bat":
                return 1
            else:
                return 0
        except:
            print("Cannot hear")
def batuser():
    c=1
    score=0
    while (c!=0):
        x=int(input("Enter your score while batting"))
        y=randrange(1,9)
        if(x==y):
            print("Sorry,you got out")
            c=c-1
        else:
            score=score+x
    print("You scored {} runs".format(score))
    return score
def balluser():
    c=1
    score=0
    while(c!=0):
        x=int(input("Enter your number while bowling"))
        y=randrange(1,9)
        if x==y:
            print("Congratulation, you took the wicket")
            c=c-1
        else:
            score=score+x
    print("The Computer score {} runs".format(score))
    return score
def batcom():
    c=1
    score=0
    while(c!=0):
        x=int(input("Enter your number while bowling"))
        y=randrange(1,9)
        if x==y:
            print("Congratulation, you took the wicket")
            c=c-1
        else:
            score=score+x
    print("The Computer score {} runs".format(score))
def ballcom():
    c=1
    score=0
    while(c!=0):
        x=int(input("Enter your score while batting"))
        y=randrange(1,9)
        if x==y:
            print("Sorry, computer took the wicket")
            c=c-1
        else:
            score=score+x
    print("You scored {} runs".format(score))
             
x=int(input("Enter your number for the toss"))
y=randrange(1,9)
if ((x+y)%2==0 and userChoice()==1) or ((x+y)%2!=0 and userChoice()==0):
    print("You won the toss")
    print(y)
    if (userBatBall()==1):
        if batuser()>balluser():
            print("Congratulations,youn won the game")
        else:
            print("Sorry, The Computer won")

    else:
        if balluser()<batuser():
            print("Congratulations, you won the game")
        else:
            print("Sorry, The Computere won")

else:
    print("You lost the toss")
    a=randrange(1,3)
    if a==1:
        print("The computer choose to bat")
        if batcom()>ballcom():
            print("Computer won")
    else:
        print("The computer choosse to ball")
        if ballcom()>batcom():
            print("You won")
    

