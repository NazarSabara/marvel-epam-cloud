import React, {useState, useEffect} from 'react';
import HeroList from './HeroList/HeroList';
import BattleResults from './BattleResults';
import {getHeroesUrl, startBattleUrl, battleTypeOptions} from './BattleConstants';
import {Form, RadioButtonGroup, Button} from 'grommet';
import './BattlePage.scss';

function BattlePage() {

    const map = {
        name : 'ff',
        gravitation : 0.25,
        oxygenLevel : 0.25,
        inhabitants : ['Human'],
        naturalCondition : 0.25
    }

    const [heroes, setHeroes] = useState([]);
    useEffect(() => {
        fetch(getHeroesUrl, {
               headers: { 'Authorization': localStorage.getItem('auth_header')}
           })
            .then(res => res.json())
            .then(json => setHeroes(json));
    }, []);

    const [resultsPopup, showPopup] = useState(false);
    const togglePopup = () => {
        showPopup(!resultsPopup);
    }

    const [results, setResults] = useState({});

    const [battleType, setBattleType] = useState('PVP');
    const [firstTeam, setFirstTeam] = useState([]);
    const [secondTeam, setSecondTeam] = useState([]);

    const handleChangeFirst = (value) => {
        setFirstTeam(value);
    }
    const handleChangeSecond = (value) => {
        setSecondTeam(value);
    }

    class DropDownItem {
      constructor(text, hero) {
        this.value = hero;
        this.text = text;
      }
    }

    return (
        <Form class='battlePageElement'>
            <RadioButtonGroup
                name="battleType"
                options={battleTypeOptions}
                value={battleType}
                onChange={(event) => setBattleType(event.target.value)}
            />
            <Form class='listForm'>
                <HeroList isTeamBattle={battleType === battleTypeOptions[1]} heroes = {heroes.map( (hero) => new DropDownItem(hero.name, hero))} onChange={handleChangeFirst}></HeroList>
                <HeroList isTeamBattle={battleType === battleTypeOptions[1]} heroes = {heroes.map( (hero) => new DropDownItem(hero.name, hero))} onChange={handleChangeSecond}></HeroList>
            </Form>
            <Button type="button" primary label="Battle" size="medium" margin="small"
             onClick={() => {
                fetch(startBattleUrl, {
                    method: 'POST',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(
                        {
                            firstTeam,
                            secondTeam,
                            battleType,
                            map})
                }).then(res => res.json())
                .then(json => setResults(json))

                togglePopup();}
                } />
                {
                    resultsPopup
                      ? <BattleResults survivors={results.survivors} closePopup={togglePopup} />
                      : null
                }
        </Form>
    );
}

export default BattlePage;