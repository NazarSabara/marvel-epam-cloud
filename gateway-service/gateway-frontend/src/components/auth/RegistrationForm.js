import React, { useState } from 'react';
import { Form, Button, TextInput, Heading} from 'grommet';
import './Auth.scss';
import {useHistory} from 'react-router-dom';
import {coreServiceBaseUrl} from '../Constants';

function RegistrationForm() {

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [email, setEmail] = useState('');

    const history = useHistory();

      return (
        <Form class = "auth">
        <Heading color="dark-2">Registration</Heading>
        <TextInput  className="input" value={username} placeholder="username" onChange={(evt) =>  {setUsername(evt.target.value)}}/>
        <TextInput  className="input" value={email} placeholder="email" onChange={(evt) =>  {setEmail(evt.target.value)}}/>
        <TextInput className="input"  value={password} type = "password" placeholder="password" onChange={(evt) =>  {setPassword(evt.target.value)}}/>
        <TextInput className="input" value={confirmPassword} type = "password" placeholder="confirm password"  onChange={(evt) =>  {setConfirmPassword(evt.target.value)}}/>
        <Button type="submit" primary label="Register" size="medium" margin="small"  onClick={() => {
            fetch(coreServiceBaseUrl + '/users', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(
                    {
                    username,
                    email,
                    password
                    })
            })
            .then(res => {
                if(res.status === 500){
                    throw new Error(res.status)
                }
                history.push("/");
                })
             .catch(error => {
                   alert("User already exists");
               });
            }
            } />
    </Form>
      );
}

export default RegistrationForm;