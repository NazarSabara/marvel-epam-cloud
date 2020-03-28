import React, { useState } from 'react';
import { Dropdown } from 'semantic-ui-react';

function HeroList(props) {

   const [selected, setSelected] = useState([]);
   const handleChange = (e, { value }) => {
    setSelected({value});
    props.onChange(value)
   }

  return (
      <Dropdown className = 'ui fluid selection dropdown'
        clearable
        onChange={handleChange}
        multiple = {props.multiple}
        search
        options={props.heroes}
        placeholder='Select Hero'
      />
  );
}

export default HeroList;