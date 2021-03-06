package com.lilithsthrone.game.dialogue.npcDialogue.dominion;

import java.util.ArrayList;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.attributes.CorruptionLevel;
import com.lilithsthrone.game.character.fetishes.Fetish;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseCombat;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.utils.InventoryInteraction;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.inventory.item.AbstractItem;
import com.lilithsthrone.game.inventory.item.ItemType;
import com.lilithsthrone.game.sex.Sex;
import com.lilithsthrone.game.sex.SexPace;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.universal.SMStanding;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.?
 * @version 0.2.2
 * @author Innoxia
 */
public class AlleywayAttackerDialogue {

	private static boolean isCanal() {
		PlaceType pt = Main.game.getActiveNPC().getLocationPlace().getPlaceType();
		return (pt == PlaceType.DOMINION_ALLEYS_CANAL_CROSSING
				|| pt == PlaceType.DOMINION_CANAL
				|| pt == PlaceType.DOMINION_CANAL_END);
	}
	
	public static final DialogueNodeOld ALLEY_ATTACK = new DialogueNodeOld("Assaulted!", "A figure jumps out from the shadows!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}
		
		@Override
		public String getContent() {

			UtilText.nodeContentSB.setLength(0);
			
			if(Main.game.getActiveNPC().getLastTimeEncountered() != -1) {
				
				if(isCanal()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Knowing that [npc.name(a)] is prowling around this area, you make sure to stay on high alert as you wander down the track that runs alongside Dominion's canal."
								+ " Just as you suspected, as you cautiously pass a collection of leafy bushes growing on one side of the path, a shadowy figure leaps out, blocking your path."
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "Knowing that [npc.name(a)] is prowling around this area, you make sure to stay on high alert, carefully checking each and every dark corner and recess as you walk through the twisting passages."
								+ " Just as you suspected, as you cautiously pass an inset doorway, a shadowy figure leaps out, blocking your path."
							+ "</p>");
				}
				
				if(Main.game.getActiveNPC().isVisiblyPregnant()){
					// Pregnant encounters:
					if(!Main.game.getActiveNPC().isReactedToPregnancy()) {
						UtilText.nodeContentSB.append("<p>"
									+ "You instantly recognise the [npc.fullRace(true)], and you instinctively jump back into a fighting stance as you expect this encounter to be much the same as the last one."
									+ " As [npc.she] steps out from the shadows, however, you notice that there's definitely something different about [npc.herHim] this time."
									+ " The consequence of ejaculating inside of [npc.herHim] is staring you right in the face, and you gulp as you see [npc.herHim] pointing down at her pregnant belly."
								+ "</p>"
								+ (Main.game.getActiveNPC().hasFetish(Fetish.FETISH_PREGNANCY)
										
										?"<p>"
											+"[npc.speech(That's right, you got me knocked up!)] she shouts, the excitement in [npc.her] voice clearly giving away the fact that [npc.she]'s got a thing for being pregnant."
											+ " After confirming that you're the father of [npc.her] children, [npc.she] continues, "
											+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
												?"[npc.speech(Don't think I'm letting you off the hook though! Pregnant [npc.girl]s get horny too, and you're gonna help me with that, understood?!)]"
												:"[npc.speech(Don't think I'm letting you off the hook though! I could do with some cash, so just give up and let me rob you already!)]")
										+"</p>"
										+ "<p style='text-align:center;'>" 
											+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and, although [npc.she] seems pleased, [npc.she] still wants to fight you!</b>"
										+ "</p>"
											
										:"<p>"
											+"[npc.speech(Look what you did, you asshole!)] [npc.she] shouts, confirming that you're the father of [npc.her] children. "
											+ "[npc.speech(Do you know how annoying it is being pregnant?! You're gonna pay for this!)]"
										+"</p>"
										+ "<p style='text-align:center;'>" 
											+ "<b style='color:" + Colour.GENERIC_SEX.toWebHexString() + ";'>You ended up getting [npc.name] pregnant, and now [npc.she] wants revenge!</b>"
										+ "</p>"));
					
					} else {
						UtilText.nodeContentSB.append("<p>"
								+ "You instantly recognise the [npc.fullRace(true)], and you jump back into a fighting stance as you expect this encounter to be much the same as the last one."
								+ " [npc.She]'s still sporting a round belly, and [npc.she] absent-mindedly strokes [npc.her] swollen bump as [npc.she] reacts to your sudden appearance in [npc.her] territory."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(You again?!)] [npc.she] shouts. [npc.speech(This'll be a lot easier if you give up right now!)]" 
							+ "</p>"
							+ "<p>"
								+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
									?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
											+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
									:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
											+ " and will most likely only rob you if you were to lose this fight.")
							+ "</p>");
					}
					
				} else {
					// Standard repeat encounter:
					UtilText.nodeContentSB.append("<p>"
								+ "You instantly recognise the [npc.fullRace(true)], and you jump back into a fighting stance as you expect this encounter to be much the same as the last one."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(You again?!)] [npc.she] shouts. [npc.speech(This'll be a lot easier if you give up right now!)]" 
							+ "</p>"
							+ "<p>"
								+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
									?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
											+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
									:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
											+ " and will most likely only rob you if you were to lose this fight.")
							+ "</p>");
				}
				
			} else {
				if(isCanal()) {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself wandering down the track that runs alongside Dominion's canal."
								+ " As you walk past a collection of leafy bushes growing on one side of the path, a shadowy figure suddenly leaps out, blocking your path."
							+ "</p>");
					
				} else {
					UtilText.nodeContentSB.append(
							"<p>"
								+ "You find yourself walking down yet another narrow passage, lined by a series of inset doorways."
								+ " As you're passing one such recess, a figure suddenly jumps out from the shadows, blocking your path."
							+ "</p>");
				}
				
				UtilText.nodeContentSB.append("<p>"
								+ "You leap back, narrowly avoiding a blow aimed at your stomach, and prepare yourself for a fight."
								+ " Looking up, you see [npc.a_fullRace(true)] grinning devilishly at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(What do we have here?)] [npc.she] asks, letting out a short laugh as [npc.she] sees that you're ready to fight [npc.herHim]. [npc.speech(It'll be a lot easier if you just give up!)]"
							+ "</p>"
							+ "<p>"
							+ (Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())
								?"Your powerful arcane aura is clearly turning [npc.herHim] on, and from [npc.her] hungry gaze that lingers on your body, you're able to get a good idea of what [npc.she] wants to do with you."
										+ " Knowing that defeat will result in being raped by this horny [npc.race], you ready yourself for a fight."
								:"Although your powerful arcane aura is turning [npc.herHim] on a little, it doesn't look as though [npc.she]'s really all that interested in your body,"
										+ " and will most likely only rob you if you were to lose this fight.")
							+ "</p>");
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "Stand up for yourself and fight [npc.name]!", Main.game.getActiveNPC());
				
			} else if (index == 2) {
				if(Main.game.getPlayer().getMoney()<250) {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "You don't have enough money to offer to pay [npc.name] off. You'll have to either fight [npc.herHim] or offer [npc.herHim] your body!", null);
				} else {
					return new Response("Offer money ("+UtilText.formatAsMoney(250, "span")+")", "Offer to pay [npc.name] 250 flames to leave you alone.", Main.game.getDefaultDialogueNoEncounter()) {
						@Override
						public void effects() {
							Main.game.getPlayer().incrementMoney(-250);
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "Wanting to avoid a fight if at all possible, you take a step backwards, fumbling about to grab a handful of flames to offer to the [npc.race]."
										+ " [pc.speech(I don't want to fight! Please, just take my money and leave me alone!)]"
									+ "</p>"
									+ "<p>"
										+ "[npc.speech(Sure, just hand it over and we'll both be on our way!)]"
										+ " [npc.name] replies, [npc.her] desire to rob you of your money obviously the motivating factor behind [npc.her] assault."
									+ "</p>"
									+ "<p>"
										+ "Stepping forwards, [npc.she] snatches the flames out of your [pc.hand], letting out a mocking laugh as [npc.she] commands,"
										+ " [npc.speech(Hah! Now get out of my sight!)]"
									+ "</p>"
									+ "<p>"
										+ "Reluctant to do anything other than what the [npc.race] suggests, you hurry on your way, thankful that you had enough money to avoid a fight."
									+ "</p>");
						}
					};
				}
				
			} else if (index == 3) {
				if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
					return new ResponseSex("Offer body", "Offer your body to [npc.name] so that you can avoid a violent confrontation.",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE), null, Fetish.FETISH_SUBMISSIVE.getAssociatedCorruptionLevel(),
							null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "Wanting to avoid a fight, and unwilling to hand over any of your money, you decide to do the only other thing you can think of, and step forwards smiling seductively at [npc.name]. "
								+ " [pc.speech(There's no need to fight. If you're looking for a bit of fun, all you had to do was ask...)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Oh really?)]"
								+ " [npc.name] replies, stepping forwards in an intimidating manner."
								+ " You remain in place, putting on your most seductive look and trying to make yourself as small as possible as you attempt to entice [npc.name] into using you for some sexual relief."
							+ "</p>"
							+ "<p>"
								+ "Much to your delight, your would-be attacker reaches up and grabs you by the [pc.arms], before pulling you forwards into a tight embrace."
								+ " Pressing [npc.her] [npc.lips] against yours, [npc.she] thrusts [npc.her] [npc.tongue] into your mouth, aggressively kissing you for a moment before pulling back, growling,"
								+ " [npc.speech(You're going to be a good [pc.girl] now, aren't you bitch?)]"
							+ "</p>"
							+ "<p>"
								+ "Whimpering in the affirmative, you surrender yourself to [npc.name]'s dominant touch, allowing yourself to be used as payment for trespassing on [npc.her] territory..."
							+ "</p>");
				} else {
					return new Response("Offer body", "You can tell that [npc.name] isn't at all interested in having sex with you. You'll either have to offer [npc.herHim] some money, or prepare for a fight!", null);
				}
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld STORM_ATTACK = new DialogueNodeOld("Attacked!", "A figure jumps out of a nearby doorway!", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public String getLabel(){
			return "Assaulted!";
		}

		@Override
		public String getContent() {
			// Storm attackers are different from alley attackers. They are not saved as persistent NPCs, so don't worry about giving any repeat-encounter descriptions.
			return "<p>"
						+ "You find yourself walking down the deserted streets of Dominion, the eerie silence broken only by the occasional crash of arcane thunder high above you."
						+ " There are many hiding places for a would-be attacker to conceal themselves in, and as you pass one such place, taking the form of a building's shadowy doorway, a figure suddenly leaps out at you."
						+ " Thankfully, as you were taking care to be alert, you quickly step backwards and manage to avoid a punch aimed at your stomach."
					+ "</p>"
					+ "<p>"
						+ "Turning to face the assailant, you see [npc.a_fullRace(true)] grinning devilishly at you."
						+ " There's no mistaking that hungry look in [npc.her] [npc.eyes], and you know that the arcane thunder has driven [npc.herHim] into a state of uncontrollable lust."
						+ " [npc.She] looks determined to claim you as [npc.her] prize, and is ready to fight you in order to claim your body."
					+ "</p>"
					+ "<p>"
						+ "[npc.speech(Aww, there's no need for that!)]"
						+ " [npc.she] teases, the desperation clearly audible in [npc.her] voice as [npc.she] sees that you're ready to fight [npc.herHim]."
						+ " [npc.speech(Just give up! Come on! I <i>need</i> a good fuck right now!)]"
					+ "</p>"
					+ "<p>"
						+ "The arcane storm, combined with your powerful aura, has filled [npc.herHim] with a wild lust, and you have a good idea of what [npc.she] wants to do with you."
						+ " Knowing that defeat will result in being raped by this desperate [npc.race], you ready yourself for a fight."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseCombat("Fight", "You find yourself fighting [npc.name]!", Main.game.getActiveNPC());
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_VICTORY = new DialogueNodeOld("Victory", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getDescription() {
			return "You have defeated [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that [npc.she]'s still got that same hungry look in [npc.her] eyes, despite [npc.her] defeat."
							+ " [npc.She] reaches down to [npc.her] crotch and starts stroking [npc.herself], making pitiful little whining noises as [npc.she] squirms on the floor."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Aaah! What are you waiting for?! Come fuck me!)] [npc.she] pleads, biting [npc.her] [npc.lip] as [npc.she] continues touching [npc.herself]."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should indulge [npc.her] request."
							+ " After all, [npc.she] <i>was</i> going to do the same to you, and [npc.she] quite clearly wants it."
							+ " Then again, maybe it's best to just leave."
						+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "[npc.Name] collapses to the floor, completely defeated."
							+ " [npc.She] looks up at you, and you see that there's a desperate look of regret in [npc.her] [npc.eyes]."
							+ " Making pitiful little whining noises, [npc.she] tries to shuffle away from you, clearly worried about what your intentions are."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(J-Just take my money and leave me alone!)] [npc.she] pleads, throwing [npc.her] "+(Main.game.getActiveNPC().isFeminine()?"purse":"wallet")+" at your feet."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should do as [npc.she] says, and leave [npc.herHim] alone."
							+ " Then again, you <i>could</i> take advantage of [npc.her] weakened, vulnerable body..."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) || !Main.game.isNonConEnabled()) {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex("Have some fun",
							"Well, [npc.she] <i>is</i> asking for it!",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY);
					
				} else if (index == 3) {
					return new ResponseSex("Have some gentle fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'gentle' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY);
					
				} else if (index == 4) {
					return new ResponseSex("Have some rough fun",
							"Well, [npc.she] <i>is</i> asking for it! (Start the sex scene in the 'rough' pace.)",
							true, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY);
					
				} else if (index == 5) {
					return new ResponseSex("Submit",
							"You're not really sure what to do now...<br/>"
								+ "Perhaps it would be best to let [npc.name] choose what to do next?",
							Util.newArrayListOfValues(Fetish.FETISH_SUBMISSIVE),
							null, CorruptionLevel.THREE_DIRTY, null, null, null,
							true, true,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "You really aren't sure what to do next, and start to feel pretty uncomfortable with the fact that you just beat up this poor [npc.race]."
								+ " Leaning down, you do the first thing that comes into your mind, and start apologising,"
								+ " [pc.speech(Sorry... I was just trying to defend myself, you know... Erm... Is there anything I can do to make it up to you?)]"
							+ "</p>"
							+ "<p>"
								+ "For a moment, a look of confusion crosses over [npc.name]'s face, but, as [npc.she] sees that you're genuinely troubled by what you've just done, an evil grin crosses [npc.her] face."
								+ " [npc.She] stands up, and, grabbing you by the [pc.arm], roughly pulls you into [npc.her] as [npc.she] growls,"
								+ " [npc.speech(How about you start by apologising properly?!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name]'s strong, dominant grip on your [pc.arm] causes you to let out a lewd little moan, and your submissive nature takes over as you do as [npc.she] asks."
								+ " [pc.speech(I'm really sorry! Please forgive me! I'll do anything! Anything you ask! Just please, don't be mad!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.Name] roughly yanks you forwards, and with a menacing growl, [npc.she] forces [npc.her] tongue into your mouth."
								+ " You let out a muffled yelp as your opponent takes charge, but as you feel [npc.her] [npc.hands] reaching down to start roughly groping your ass,"
									+ " you realise that you couldn't be happier with how things have turned out..."
							+ "</p>");
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way...", null){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else if (index == 2) {
					return new ResponseSex(
							"Rape [npc.herHim]", "[npc.She] needs to be punished for attacking you like that...",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] firmly in your embrace..."
							+ "</p>");
					
				} else if (index == 3) {
					return new ResponseSex("Rape [npc.herHim] (gentle)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'gentle' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_GENTLE;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you take hold of [npc.name]'s [npc.arm], and, pulling [npc.herHim] to [npc.her] feet, you start pressing yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you hold [npc.herHim] in your embrace..."
							+ "</p>");
					
				} else if (index == 4) {
					return new ResponseSex("Rape [npc.herHim] (rough)", "[npc.She] needs to be punished for attacking you like that... (Start the sex scene in the 'rough' pace.)",
							Util.newArrayListOfValues(Fetish.FETISH_NON_CON_DOM), null, Fetish.FETISH_NON_CON_DOM.getAssociatedCorruptionLevel(), null, null, null,
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.DOM_ROUGH;
									}
									return null;
								}
							},
							AFTER_SEX_VICTORY,
							"<p>"
								+ "Reaching down, you grab [npc.name]'s [npc.arm], and, roughly yanking [npc.herHim] to [npc.her] feet, you start forcefully grinding yourself up against [npc.herHim]."
								+ " Seeing the lustful look in your [pc.eyes], [npc.she] lets out a little [npc.sob], desperately trying to struggle out of your grip as you firmly hold [npc.herHim] in your embrace..."
							+ "</p>");
					
				} else if (index == 5) {
					return new Response("Submit",
							"You can't submit to [npc.herHim], as [npc.she] has no interest in having sex with you!",
							null);
					
				} else if (index == 6) {
					return new ResponseEffectsOnly("Inventory", "Now that you've defeated [npc.name], there's nothing stopping you from helping yourself to [npc.her] clothing and items..."){
						@Override
						public void effects() {
							Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
						}
					};
					
				} else if (index == 10) {
					return new Response(
							"Remove character",
							"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
							AFTER_COMBAT_VICTORY){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
						@Override
						public void effects() {
							Main.game.banishNPC(Main.game.getActiveNPC());
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};

	public static final DialogueNodeOld AFTER_COMBAT_DEFEAT = new DialogueNodeOld("Defeat", "", true) {
		private static final long serialVersionUID = 1L;
		
		Value<String, AbstractItem> potion = null;
		
		@Override
		public String getDescription() {
			return "You have been defeated by [npc.name]!";
		}

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().hasTransformationFetish() && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) ) {
				potion = Main.game.getActiveNPC().getTransfomativePotion(true);
				
//				System.out.println("Potion Check 1"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue().getName()); 
				
				if(potion == null) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(You're my perfect little "
											+Main.game.getActiveNPC().getPreferredBodyDescription("b")
											+ " now! Don't forget bitch, <i>I'm</i> the one in charge!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
					
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down and pushing you to the ground."
							+ "</p>"
							+ "<p>"
								+ "As [npc.she] pins you to the floor, [npc.she] produces a curious little bottle from somewhere out of sight, and shakes it from side to side, grinning."
								+ " [npc.speech(I think you could do with some <i>improvements</i>! I'm going to turn you into my perfect "+Main.game.getActiveNPC().getPreferredBodyDescription("b")+"!)]"
							+ "</p>"
							+ "<p>"
								+ "[npc.She] pulls out the little stopper from the top of the bottle, and as you open your mouth to protest, [npc.she] suddenly shoves the neck past your [pc.lips+]."
								+ " As the sickly-sweet fluid pours out into your mouth, you let out a muffled whine; the only act of resistance that you're able to summon in your current state."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Come on! Swallow it all down already!)] [npc.she] growls, throwing the now-empty vessel to one side as [npc.she] tries to force you to swallow the strange fluid..."
							+ "</p>");
				}
			}
				
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
								+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
							+ "</p>"
							+ "<p>"
								+ "Pulling you to your feet, [npc.name] starts grinding [npc.herself] up against you, [npc.moaning] into your [pc.ear] as [npc.she] starts groping your body."
							+ "</p>"
							+ "<p>"
								+ "[npc.speech(Don't try anything, bitch! <i>I'm</i> the one in charge here!)] [npc.she] growls, before pulling you into a forceful kiss."
							+ "</p>");
				
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "You can't carry on fighting any more, and you feel your [pc.legs] giving out beneath you as you collapse to the ground, defeated."
							+ " A mocking laugh causes you to look up, and you see [npc.name] grinning down at you."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Hah! That was too easy!)] [npc.she] says, before leaning down to grab one of your [pc.arms]."
						+ "</p>"
						+ "<p>"
							+ "Pulling you to your feet, [npc.name] pushes you against a nearby wall, before demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(Don't even <i>think</i> about reporting this to the enforcers!)] [npc.she] growls down at you, before turning around and running off."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().hasTransformationFetish() && potion != null && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer()) ) {
				
//				System.out.println("Potion Check 2"); 
//				System.out.println(potion); 
//				System.out.println(potion.getValue()); 
				
				if (index == 1) {
					if(Main.game.getPlayer().hasFetish(Fetish.FETISH_TRANSFORMATION_RECEIVING)) {
						return new Response("Spit",
								"Due to your <b style='color:"+Colour.FETISH.toWebHexString()+";'>"+Fetish.FETISH_TRANSFORMATION_RECEIVING.getName(Main.game.getPlayer())
									+"</b> fetish, you love being transformed so much that you can't bring yourself to spit out the transformative liquid!",
								null);
					} else {
						return new Response("Spit", "Spit out the potion.", AFTER_COMBAT_TRANSFORMATION_REFUSED);
					}
					
				} else if (index == 2) {
					ArrayList<Fetish> applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_TRANSFORMATION_RECEIVING);
					CorruptionLevel applicableCorrutionLevel = Fetish.FETISH_TRANSFORMATION_RECEIVING.getAssociatedCorruptionLevel();
					
					if(potion.getValue().getItemType() == ItemType.FETISH_REFINED) {
						applicableFetishes = Util.newArrayListOfValues(Fetish.FETISH_KINK_RECEIVING);
						applicableCorrutionLevel = Fetish.FETISH_KINK_RECEIVING.getAssociatedCorruptionLevel();
					}
					
					return new Response("Swallow", "Do as you're told and swallow the strange potion.", AFTER_COMBAT_TRANSFORMATION,
							applicableFetishes,
							applicableCorrutionLevel,
							null,
							null,
							null){
						@Override
						public void effects(){
							Util.Value<String, AbstractItem> potion = Main.game.getActiveNPC().getTransfomativePotion();
							
//							System.out.println("Potion Check 3"); 
//							System.out.println(potion.getValue().getName()); 
//							System.out.println(potion); 
							
							Main.game.getTextStartStringBuilder().append(
									"<p>"
										+ "[npc.Name] steps back, grinning down at you as you obediently swallow the strange liquid."
										+ " [npc.speech(Good [pc.girl]! "+potion.getKey()+")]"
									+ "</p>"
									+ "<p>"
										+Main.game.getActiveNPC().useItem(potion.getValue(), Main.game.getPlayer(), false, true)
									+"</p>");
						}
					};
					
				} else {
					return null;
				}
			} else {
				if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.getActiveNPC().isWillingToRape(Main.game.getPlayer())) {
					if (index == 1) {
						return new ResponseSex("Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
								AFTER_SEX_DEFEAT,
								"<p>"
									+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally pulling away."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
								+ "</p>");
						
					} else if (index == 2) {
						return new ResponseSex("Eager Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.isPlayer()) {
											return SexPace.SUB_EAGER;
										}
										return null;
									}
								},
								AFTER_SEX_DEFEAT,
								"<p>"
									+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
									+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
								+ "</p>");
						
					} else if (index == 3 && Main.game.isNonConEnabled()) {
						return new ResponseSex("Resist Sex",
								"[npc.Name] forces [npc.herself] on you...",
								false, false,
								new SMStanding(
										Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
										Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
									@Override
									public SexPace getStartingSexPaceModifier(GameCharacter character) {
										if(character.isPlayer()) {
											return SexPace.SUB_RESISTING;
										}
										return null;
									}
								},
								AFTER_SEX_DEFEAT,
								"<p>"
									+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
									+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
									+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
								+ "</p>");
						
					} else {
						return null;
					}
					
				} else {
					if (index == 1) {
						return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
							@Override
							public DialogueNodeOld getNextDialogue() {
								return Main.game.getDefaultDialogueNoEncounter();
							}
						};
						
					} else {
						return null;
					}
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION_REFUSED = new DialogueNodeOld("Avoided Transformation", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+ "<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+Main.game.getActiveNPC().getPreferredBodyDescription("b")
							+" next time! For now, I'm going to get some fun out of you just as you are!)]"
						+ "</p>");
			
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "Despite [npc.name]'s best efforts, you manage to twist your head to one side and spit the strange fluid out onto the floor."
							+ " Your response is met by an anguished groan from your assailant, and, turning your head back up to look at them once more, you see them snarling down angrily at you,"
							+ " [npc.speech(You <i>"+(Main.game.getPlayer().isFeminine()?"bitch":"bastard")+"</i>! Do you know how much that cost me?!)]"
						+ "</p>"
						+"<p>"
							+ "After shouting down into your face, [npc.name] stands up, pulling you roughly to your [pc.feet] as [npc.she] does so, before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] shoves you down to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(This money of yours is going to pay for your next potion!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! And don't you dare refuse to swallow next time!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you to recover from your ordeal and continue on your way..."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_COMBAT_TRANSFORMATION = new DialogueNodeOld("Transformed", "", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pressing [npc.herself] against you and forcing you into a wet kiss."
							+ " You're powerless to resist [npc.her] advances, and as [npc.her] [npc.hands] reach around to give your [pc.ass+] a squeeze, [npc.she] laughs,"
							+ " [npc.speech(I'll turn you into my perfect little "
							+ Main.game.getActiveNPC().getPreferredBodyDescription("b")
							+ "! Now for the real fun!)]"
						+ "</p>");
			
			} else {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you struggle to recover from your transformation, [npc.name] pulls you to your [pc.feet], before pushing you against a nearby wall and demanding that you hand over your money."
							+ " Reluctantly, you do as [npc.she] says, and, after giving [npc.herHim] some of your cash, [npc.she] roughly pushes you to the floor once more."
						+ "</p>"
						+ "<p>"
							+ "[npc.speech(You're not good enough for me to be interested in you just yet!)] [npc.she] growls down at you. [npc.speech(Come back and pay me another visit, <i>or else</i>! I'm going to turn you into my perfect little "
								+Main.game.getActiveNPC().getPreferredBodyDescription("b")+"!)]"
						+ "</p>"
						+ "<p>"
							+ "With that, [npc.she] turns around and runs off, leaving you panting and sweating as you attempt to recover from the transformations that were just forced upon you..."
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer())) {
				if (index == 1) {
					return new ResponseSex("Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))),
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and [npc.she] continues passionately making out with you for a few moments, before finally breaking away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you realise that [npc.she]'s probably not going to be content with just a kiss..."
							+ "</p>");
					
				} else if (index == 2) {
					return new ResponseSex("Eager Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_EAGER;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you eagerly lean into [npc.herHim], passionately returning [npc.her] kiss for a few moments, before [npc.she] breaks away from you."
								+ " Giving you an evil grin, [npc.she] hungrily licks [npc.her] [npc.lips], and you feel a rush of excitement as you realise that [npc.she]'s going to want more than just a kiss..."
							+ "</p>");
					
				} else if (index == 3 && Main.game.isNonConEnabled()) {
					return new ResponseSex("Resist Sex",
							"[npc.Name] forces [npc.herself] on you...",
							false, false,
							new SMStanding(
									Util.newHashMapOfValues(new Value<>(Main.game.getActiveNPC(), SexPositionSlot.STANDING_DOMINANT)),
									Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STANDING_SUBMISSIVE))) {
								@Override
								public SexPace getStartingSexPaceModifier(GameCharacter character) {
									if(character.isPlayer()) {
										return SexPace.SUB_RESISTING;
									}
									return null;
								}
							},
							AFTER_SEX_DEFEAT,
							"<p>"
								+ "[npc.Name]'s [npc.arms] wrap around your back, and you let out a distressed cry as [npc.she] pulls you into a forceful kiss."
								+ " Summoning the last of your strength, you desperately try to push [npc.herHim] away, pleading for [npc.herHim] to stop."
								+ " Giving you an evil grin, [npc.she] ignores your protests, and as you see [npc.herHim] hungrily licking [npc.her] [npc.lips], you realise that [npc.she]'s not going to let you go..."
							+ "</p>");
					
				} else {
					return null;
				}
				
			} else {
				if (index == 1) {
					return new Response("Continue", "Carry on your way.", AFTER_COMBAT_DEFEAT){
						@Override
						public DialogueNodeOld getNextDialogue() {
							return Main.game.getDefaultDialogueNoEncounter();
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_VICTORY = new DialogueNodeOld("Step back", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 15;
		}
		
		@Override
		public String getDescription(){
			return "Now that you've had your fun, you can step back and leave [npc.name] to recover.";
		}

		@Override
		public String getContent() {
			if(!Main.game.getActiveNPC().isAttractedTo(Main.game.getPlayer()) && Main.game.isNonConEnabled()) {
				return UtilText.parse(Main.game.getActiveNPC(),
						"<p>"
							+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a thankful sob as [npc.she] realises that you've finished."
							+ " [npc.She] starts frantically gathering [npc.her] belongings, obviously quite keen to make [npc.her] exit before you decide to do anything else..."
						+ "</p>");
				
			} else {
				if(Sex.getNumberOfOrgasms(Sex.getActivePartner()) >= 1) {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, totally worn out from [npc.her] orgasm"+(Sex.getNumberOfOrgasms(Sex.getActivePartner()) > 1?"s":"")+"."
								+ " Looking up at you, a satisfied smile settles across [npc.her] face, and you realise that you gave [npc.herHim] exactly what [npc.she] wanted."
							+ "</p>");
				} else {
					return UtilText.parse(Main.game.getActiveNPC(),
							"<p>"
								+ "As you step back from [npc.name], [npc.she] sinks to the floor, letting out a desperate whine as [npc.she] realises that you've finished."
								+ " [npc.Her] [npc.hands] dart down between [npc.her] [npc.legs], and [npc.she] frantically starts masturbating as [npc.she] seeks to finish what you started."
							+ "</p>");
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else if (index == 6) {
				return new ResponseEffectsOnly("Inventory", "There's nothing stopping you from helping yourself to [npc.name]'s clothing and items..."){
					@Override
					public void effects() {
						Main.mainController.openInventory(Main.game.getActiveNPC(), InventoryInteraction.FULL_MANAGEMENT);
					}
				};
				
			} else if (index == 10) {
				return new Response(
						"Remove character",
						"Scare [npc.name] away. <b>This will remove [npc.herHim] from this area, allowing another character to move into this tile.</b>",
						AFTER_COMBAT_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue() {
						return Main.game.getDefaultDialogueNoEncounter();
					}
					@Override
					public void effects() {
						Main.game.banishNPC(Main.game.getActiveNPC());
					}
				};
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_SEX_DEFEAT = new DialogueNodeOld("Collapse", "", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 30;
		}
		
		@Override
		public String getDescription(){
			return "You're completely worn out from [npc.name]'s dominant treatment, and need a while to recover.";
		}

		@Override
		public String getContent() {
			return UtilText.parse(Main.game.getActiveNPC(),
					"<p>"
						+ "As [npc.name] steps back and sorts [npc.her] clothes out, you sink to the floor, totally worn out from [npc.her] dominant treatment of you."
						+ " [npc.She] looks down at you, and you glance up to see a very satisfied smile cross [npc.her] face."
						+ " [npc.She] leans down and pats you on the head."
						+ " [npc.speech(We should do this again some time!)]"
					+ "</p>"
					+ "<p>"
						+ "With that, [npc.she] walks off, leaving you panting on the floor."
						+ " It takes a little while for you to recover from your ordeal, but eventually you feel strong enough to get your things in order and carry on your way."
					+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Continue", "Carry on your way.", AFTER_SEX_VICTORY){
					@Override
					public DialogueNodeOld getNextDialogue(){
						return Main.game.getDefaultDialogueNoEncounter();
					}
				};
				
			} else {
				return null;
			}
		}
	};
}
