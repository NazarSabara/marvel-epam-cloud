import React, { useState } from 'react';
import { Dropdown } from 'semantic-ui-react';

function HeroList(props) {

   const handleChange = (e, { value }) => {
    props.onChange(value);
   }

  return (
      <Dropdown id ='dropDown' 
        className = 'ui fluid selection dropdown'
        clearable
        onChange={handleChange}
        multiple = {props.isTeamBattle}
        search
        options={props.heroes}
        placeholder='Select Hero'
      />
  );
}

export default HeroList;