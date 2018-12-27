FROM node:10.13.0

RUN mkdir /usr/src/workoutapp
WORKDIR /usr/src/workoutapp

RUN npm install -g @angular/cli@7.0.6 

COPY . /usr/src/app

RUN npm install --save

CMD ng serve --host 0.0.0.0 --port 4200
