import React from 'react';
import { Form, Button, TextInput, Heading} from 'grommet';
import './Auth.scss';

function RegistrationForm() {
  return (
    <Form class = "auth">
    <Heading color="dark-2">Registration</Heading>
    <TextInput  className="input" placeholder="username"/>
    <TextInput className="input" type = "password" placeholder="password"/>
    <TextInput className="input" type = "password" placeholder="confirm password"/>
    <Button type="submit" primary label="Register" size="medium" margin="small"/>
</Form>
  );
}

export default RegistrationForm;