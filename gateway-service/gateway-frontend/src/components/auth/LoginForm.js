import React from 'react';
import { Form, Button, TextInput, Heading, Anchor} from 'grommet';
import {Redirect} from 'react-router-dom';
import './Auth.scss';

function LoginForm() {

  return (
        <Form class = "auth">
            <Heading className="marginLabel" color="dark-2">Login</Heading>
            <TextInput className="input" placeholder="username"/>
            <TextInput className="input" type = "password" placeholder="password"/>
            <Button type="submit" primary label="Login" size="medium" margin="small"/>
            <Anchor href="/register" primary label="Register now" alignSelf="end" margin="medium"/>
        </Form>
  );
}

export default LoginForm;