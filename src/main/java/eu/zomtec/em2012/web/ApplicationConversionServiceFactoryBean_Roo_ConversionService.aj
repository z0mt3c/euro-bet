// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package eu.zomtec.em2012.web;

import eu.zomtec.em2012.domain.Bet;
import eu.zomtec.em2012.domain.BetUser;
import eu.zomtec.em2012.domain.BetUserRole;
import eu.zomtec.em2012.domain.Game;
import eu.zomtec.em2012.domain.GameGroup;
import eu.zomtec.em2012.domain.Team;
import eu.zomtec.em2012.domain.TeamGroup;
import eu.zomtec.em2012.web.ApplicationConversionServiceFactoryBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Bet, String> ApplicationConversionServiceFactoryBean.getBetToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.Bet, java.lang.String>() {
            public String convert(Bet bet) {
                return new StringBuilder().append(bet.getScoreHome()).append(" ").append(bet.getScoreAway()).append(" ").append(bet.getLastBetChange()).append(" ").append(bet.getLastBetCalculation()).toString();
            }
        };
    }
    
    public Converter<Long, Bet> ApplicationConversionServiceFactoryBean.getIdToBetConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.Bet>() {
            public eu.zomtec.em2012.domain.Bet convert(java.lang.Long id) {
                return Bet.findBet(id);
            }
        };
    }
    
    public Converter<String, Bet> ApplicationConversionServiceFactoryBean.getStringToBetConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.Bet>() {
            public eu.zomtec.em2012.domain.Bet convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Bet.class);
            }
        };
    }
    
    public Converter<BetUser, String> ApplicationConversionServiceFactoryBean.getBetUserToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.BetUser, java.lang.String>() {
            public String convert(BetUser betUser) {
                return new StringBuilder().append(betUser.getUsername()).append(" ").append(betUser.getPassword()).toString();
            }
        };
    }
    
    public Converter<Long, BetUser> ApplicationConversionServiceFactoryBean.getIdToBetUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.BetUser>() {
            public eu.zomtec.em2012.domain.BetUser convert(java.lang.Long id) {
                return BetUser.findBetUser(id);
            }
        };
    }
    
    public Converter<String, BetUser> ApplicationConversionServiceFactoryBean.getStringToBetUserConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.BetUser>() {
            public eu.zomtec.em2012.domain.BetUser convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BetUser.class);
            }
        };
    }
    
    public Converter<BetUserRole, String> ApplicationConversionServiceFactoryBean.getBetUserRoleToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.BetUserRole, java.lang.String>() {
            public String convert(BetUserRole betUserRole) {
                return new StringBuilder().append(betUserRole.getName()).toString();
            }
        };
    }
    
    public Converter<Long, BetUserRole> ApplicationConversionServiceFactoryBean.getIdToBetUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.BetUserRole>() {
            public eu.zomtec.em2012.domain.BetUserRole convert(java.lang.Long id) {
                return BetUserRole.findBetUserRole(id);
            }
        };
    }
    
    public Converter<String, BetUserRole> ApplicationConversionServiceFactoryBean.getStringToBetUserRoleConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.BetUserRole>() {
            public eu.zomtec.em2012.domain.BetUserRole convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), BetUserRole.class);
            }
        };
    }
    
    public Converter<Game, String> ApplicationConversionServiceFactoryBean.getGameToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.Game, java.lang.String>() {
            public String convert(Game game) {
                return new StringBuilder().append(game.getKickOff()).append(" ").append(game.getScoreHome()).append(" ").append(game.getScoreAway()).append(" ").append(game.getLastScoreUpdate()).toString();
            }
        };
    }
    
    public Converter<Long, Game> ApplicationConversionServiceFactoryBean.getIdToGameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.Game>() {
            public eu.zomtec.em2012.domain.Game convert(java.lang.Long id) {
                return Game.findGame(id);
            }
        };
    }
    
    public Converter<String, Game> ApplicationConversionServiceFactoryBean.getStringToGameConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.Game>() {
            public eu.zomtec.em2012.domain.Game convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Game.class);
            }
        };
    }
    
    public Converter<GameGroup, String> ApplicationConversionServiceFactoryBean.getGameGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.GameGroup, java.lang.String>() {
            public String convert(GameGroup gameGroup) {
                return new StringBuilder().append(gameGroup.getName()).append(" ").append(gameGroup.getSortOrder()).toString();
            }
        };
    }
    
    public Converter<Long, GameGroup> ApplicationConversionServiceFactoryBean.getIdToGameGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.GameGroup>() {
            public eu.zomtec.em2012.domain.GameGroup convert(java.lang.Long id) {
                return GameGroup.findGameGroup(id);
            }
        };
    }
    
    public Converter<String, GameGroup> ApplicationConversionServiceFactoryBean.getStringToGameGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.GameGroup>() {
            public eu.zomtec.em2012.domain.GameGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), GameGroup.class);
            }
        };
    }
    
    public Converter<Team, String> ApplicationConversionServiceFactoryBean.getTeamToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.Team, java.lang.String>() {
            public String convert(Team team) {
                return new StringBuilder().append(team.getName()).append(" ").append(team.getExternalTeamId()).toString();
            }
        };
    }
    
    public Converter<Long, Team> ApplicationConversionServiceFactoryBean.getIdToTeamConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.Team>() {
            public eu.zomtec.em2012.domain.Team convert(java.lang.Long id) {
                return Team.findTeam(id);
            }
        };
    }
    
    public Converter<String, Team> ApplicationConversionServiceFactoryBean.getStringToTeamConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.Team>() {
            public eu.zomtec.em2012.domain.Team convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Team.class);
            }
        };
    }
    
    public Converter<TeamGroup, String> ApplicationConversionServiceFactoryBean.getTeamGroupToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<eu.zomtec.em2012.domain.TeamGroup, java.lang.String>() {
            public String convert(TeamGroup teamGroup) {
                return new StringBuilder().append(teamGroup.getName()).toString();
            }
        };
    }
    
    public Converter<Long, TeamGroup> ApplicationConversionServiceFactoryBean.getIdToTeamGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, eu.zomtec.em2012.domain.TeamGroup>() {
            public eu.zomtec.em2012.domain.TeamGroup convert(java.lang.Long id) {
                return TeamGroup.findTeamGroup(id);
            }
        };
    }
    
    public Converter<String, TeamGroup> ApplicationConversionServiceFactoryBean.getStringToTeamGroupConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, eu.zomtec.em2012.domain.TeamGroup>() {
            public eu.zomtec.em2012.domain.TeamGroup convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), TeamGroup.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getBetToStringConverter());
        registry.addConverter(getIdToBetConverter());
        registry.addConverter(getStringToBetConverter());
        registry.addConverter(getBetUserToStringConverter());
        registry.addConverter(getIdToBetUserConverter());
        registry.addConverter(getStringToBetUserConverter());
        registry.addConverter(getBetUserRoleToStringConverter());
        registry.addConverter(getIdToBetUserRoleConverter());
        registry.addConverter(getStringToBetUserRoleConverter());
        registry.addConverter(getGameToStringConverter());
        registry.addConverter(getIdToGameConverter());
        registry.addConverter(getStringToGameConverter());
        registry.addConverter(getGameGroupToStringConverter());
        registry.addConverter(getIdToGameGroupConverter());
        registry.addConverter(getStringToGameGroupConverter());
        registry.addConverter(getTeamToStringConverter());
        registry.addConverter(getIdToTeamConverter());
        registry.addConverter(getStringToTeamConverter());
        registry.addConverter(getTeamGroupToStringConverter());
        registry.addConverter(getIdToTeamGroupConverter());
        registry.addConverter(getStringToTeamGroupConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
