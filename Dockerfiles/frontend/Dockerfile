FROM node:latest
WORKDIR /frontend
COPY ./JavaScript/package.json .
RUN npm install
COPY . .
CMD ["npm", "run", "dev"]