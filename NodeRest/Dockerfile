# base image
FROM node:8.4.0

# variables
ENV NODE_ENV=dev


WORKDIR /usr/src/app
COPY package*.json ./

#RUN npm install
COPY . .

# EXPOSE 3891
CMD [ "npm", "start"]