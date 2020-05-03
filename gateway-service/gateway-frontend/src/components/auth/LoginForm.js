import React, { useState} from 'react';
import { Form, Button, TextInput, Heading, Anchor} from 'grommet';
import {useHistory} from 'react-router-dom';
import './Auth.scss';
import {coreServiceBaseUrl} from '../Constants';

function LoginForm() {

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const history = useHistory();

  return (
        <Form class = "auth">
            <Heading className="marginLabel" color="dark-2">Login</Heading>
            <TextInput className="input"value={username} placeholder="username" onChange={(evt) =>  {setUsername(evt.target.value)}}/>
            <TextInput className="input" type = "password" value={password} type = "password" placeholder="password" onChange={(evt) =>  {setPassword(evt.target.value)}}/>
            <Button type="submit" primary label="Login" size="medium" margin="small" onClick={() => {
                     fetch(coreServiceBaseUrl + '/auth', {
                         headers: { 'Authorization': 'Basic ' + window.btoa(username + ":" + password)}
                     })
                         .then(res => {
                            if(res.status === 401){
                                throw new Error(res.status)
                            }
                            localStorage.setItem('auth_header', 'Basic ' + window.btoa(username + ":" + password));
                            history.push("/battle");
                            })
                         .catch(error => {
                               alert("Wrong username or password");
                           });
                     }
                 } />
            <Anchor href="/register" primary label="Register now" alignSelf="end" margin="medium"/>
        </Form>
  );
}

export default LoginForm;