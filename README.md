# About Repository
- - -
## Purpose 
> Project to implement authentication functionality using Jsonwebtoken in Java

## Deployment
```shell
sudo sh deploy.sh {db.url} {db.username} {db.password} {port} {image.name} {image.version}
```
- - -
## Access address
```shell
{AWS EC2 address}:{port}
```
- - -
## Endpoint
- - -
### UserJoin
POST `/api/v1/users/join`
RequestBody 
> userName   
> password   
> email

### UserLogin
POST `/api/v1/users/login`
RequestBody
> userName   
> password

### Post Review
POST `/api/v1/reviews`
- - -