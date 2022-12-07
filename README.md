- - -
# About Project

## ▶ Purpose 
> The purpose of this project was created to provide hospital information management and inquiry services nationwide.
- - -
## ▶ Recents
> Project to implement authentication functionality using Jsonwebtoken in Java.
- - -
## ▶ Deployment
```shell
sudo sh deploy.sh {db.url} {db.username} {db.password} {port} {image.name} {image.version}
```
- - -
## ▶ Access address
```shell
{AWS EC2 address}:{port}
```
- - -
## ▶ Endpoint

### ◆ UserJoin
POST `/api/v1/users/join`
RequestBody 
> userName   
> password   
> email

### ◆ UserLogin
POST `/api/v1/users/login`
RequestBody
> userName   
> password

### ◆ Post Hospital Review
POST `/api/v1/hospitals/{id}/reviews`
RequestBody
> userName   
> title
> content

### ◆ Inquiry about one Review
GET `/api/v1/reviews/{id}`

### ◆ Inquiry about hospital Reviews
GET `/api/v1/hospitals/{id}/reviews`

### ◆ Inquiry Hospital with its Reviews
GET `/api/v1/hospitals/{id}`
- - -
## ▶ ERD architecture
![readmeErdVisit](./img/erdVisit.png)
- - -