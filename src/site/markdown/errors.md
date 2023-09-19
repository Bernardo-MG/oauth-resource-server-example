# Errors

The `ErrorResponseAuthenticationEntryPoint` handles authentication errors and transforms them into a error response. It will also log the failed request.

## Response structure

```
{
   "errors": [
      {
         "message": "Unauthorized"
      }
   ]
}
```