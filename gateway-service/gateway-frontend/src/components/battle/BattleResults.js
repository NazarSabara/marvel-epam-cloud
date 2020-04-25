import React from 'react';
import {Button, Box, Drop, List, Heading} from 'grommet'

function BattleResults(props) {

 return (
    <Box
      direction="row"
      pad="medium"
      className='popup'
    >
      <Drop
        border={{ color: 'brand', size: 'large' }}
        className='results'>
         <Heading alignSelf="center">Survivors:</Heading>
         <List alignSelf="center" data={props.survivors}/>
         <Button alignSelf="center" type="button" className="btn" primary label="Close results" size="medium" margin="small" onClick={props.closePopup}/>
      </Drop>
    </Box>
  );
}

export default BattleResults;