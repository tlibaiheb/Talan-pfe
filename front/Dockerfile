FROM node:latest AS ui-build
WORKDIR /app
COPY package.json package-lock.json ./   
RUN npm install
COPY . .
RUN npm run build --configuration=production
FROM nginx:latest
COPY default.conf /etc/nginx/conf.d/default.conf
COPY --from=ui-build /app/dist/front/browser /usr/share/nginx/html
EXPOSE 80
ENTRYPOINT ["nginx", "-g", "daemon off;"]

