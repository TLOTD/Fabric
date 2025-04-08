package net.tlotd.entity.client;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;
import net.tlotd.entity.animation.ModAnimations;
import net.tlotd.entity.custom.TRexEntity;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class TRexModel<T extends TRexEntity> extends SinglePartEntityModel<T> {
	private final ModelPart t_rex;
	private final ModelPart head;
	private final ModelPart upper_jaw;
	private final ModelPart nose;
	private final ModelPart lower_jaw;
	private final ModelPart body;
	private final ModelPart lungs;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart left_leg_upper;
	private final ModelPart left_leg_lower;
	private final ModelPart left_foot;
	private final ModelPart right_leg;
	private final ModelPart right_leg_upper;
	private final ModelPart right_leg_lower;
	private final ModelPart right_foot;
	public TRexModel(ModelPart root) {
		this.t_rex = root.getChild("t_rex");
		this.head = this.t_rex.getChild("head");
		this.upper_jaw = this.head.getChild("upper_jaw");
		this.nose = this.upper_jaw.getChild("nose");
		this.lower_jaw = this.head.getChild("lower_jaw");
		this.body = this.t_rex.getChild("body");
		this.lungs = this.body.getChild("lungs");
		this.left_arm = this.t_rex.getChild("left_arm");
		this.right_arm = this.t_rex.getChild("right_arm");
		this.left_leg = this.t_rex.getChild("left_leg");
		this.left_leg_upper = this.left_leg.getChild("left_leg_upper");
		this.left_leg_lower = this.left_leg.getChild("left_leg_lower");
		this.left_foot = this.left_leg.getChild("left_foot");
		this.right_leg = this.t_rex.getChild("right_leg");
		this.right_leg_upper = this.right_leg.getChild("right_leg_upper");
		this.right_leg_lower = this.right_leg.getChild("right_leg_lower");
		this.right_foot = this.right_leg.getChild("right_foot");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData t_rex = modelPartData.addChild("t_rex", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = t_rex.addChild("head", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -92.0F, -45.0F));

		ModelPartData cube_r1 = head.addChild("cube_r1", ModelPartBuilder.create().uv(244, 107).cuboid(0.0F, 0.0F, -12.0F, 28.0F, 16.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.7433F, -17.4868F, 1.5708F, -0.5236F, -1.5708F));

		ModelPartData cube_r2 = head.addChild("cube_r2", ModelPartBuilder.create().uv(352, 0).cuboid(0.0F, -6.0F, -12.0F, 17.0F, 6.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -6.5134F, -27.7714F, 1.5708F, -1.2217F, -1.5708F));

		ModelPartData cube_r3 = head.addChild("cube_r3", ModelPartBuilder.create().uv(248, 0).cuboid(-15.0F, -72.0F, -12.0F, 28.0F, 16.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 39.0F, 30.0F, 1.5708F, -0.8727F, -1.5708F));

		ModelPartData upper_jaw = head.addChild("upper_jaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -20.0F, -23.0F));

		ModelPartData cube_r4 = upper_jaw.addChild("cube_r4", ModelPartBuilder.create().uv(80, 263).cuboid(-87.0F, -58.0F, -10.0F, 34.0F, 4.0F, 20.0F, new Dilation(0.0F))
		.uv(68, 315).cuboid(-41.0F, -80.0F, 12.0F, 12.0F, 22.0F, 4.0F, new Dilation(0.0F))
		.uv(284, 325).cuboid(-53.0F, -84.0F, 12.0F, 12.0F, 26.0F, 6.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-53.0F, -84.0F, -18.0F, 12.0F, 26.0F, 6.0F, new Dilation(0.0F))
		.uv(216, 0).cuboid(-41.0F, -80.0F, -16.0F, 12.0F, 22.0F, 4.0F, new Dilation(0.0F))
		.uv(196, 241).cuboid(-53.0F, -82.0F, -12.0F, 28.0F, 24.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 54.0F, 47.0F, 1.5708F, -1.4835F, -1.5708F));

		ModelPartData cube_r5 = upper_jaw.addChild("cube_r5", ModelPartBuilder.create().uv(464, 0).cuboid(0.0F, -24.0F, -24.0F, 0.0F, 24.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(16.0F, -10.6908F, 6.2066F, 2.8912F, -1.2114F, -2.9066F));

		ModelPartData cube_r6 = upper_jaw.addChild("cube_r6", ModelPartBuilder.create().uv(464, -24).cuboid(0.0F, -24.0F, 0.0F, 0.0F, 24.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(-16.0F, -10.6908F, 6.2066F, 0.2504F, -1.2114F, -0.235F));

		ModelPartData nose = upper_jaw.addChild("nose", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -8.5494F, -29.7436F));

		ModelPartData cube_r7 = nose.addChild("cube_r7", ModelPartBuilder.create().uv(0, 173).cuboid(-36.0F, -22.0F, -12.0F, 36.0F, 22.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 9.3893F, 18.8902F, 1.5708F, -1.4835F, -1.5708F));

		ModelPartData lower_jaw = head.addChild("lower_jaw", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -22.0F, -22.0F));

		ModelPartData cube_r8 = lower_jaw.addChild("cube_r8", ModelPartBuilder.create().uv(374, 30).cuboid(-39.0F, -70.0F, 12.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F))
		.uv(352, 30).cuboid(-39.0F, -70.0F, -14.0F, 9.0F, 6.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 56.0F, 46.0F, 1.5708F, -1.309F, -1.5708F));

		ModelPartData cube_r9 = lower_jaw.addChild("cube_r9", ModelPartBuilder.create().uv(320, 325).cuboid(-19.0F, -70.0F, 12.0F, 12.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(200, 197).cuboid(-30.0F, -70.0F, 12.0F, 11.0F, 12.0F, 5.0F, new Dilation(0.0F))
		.uv(316, 201).cuboid(-19.0F, -70.0F, -16.0F, 12.0F, 12.0F, 4.0F, new Dilation(0.0F))
		.uv(106, 18).cuboid(-30.0F, -70.0F, -17.0F, 11.0F, 12.0F, 5.0F, new Dilation(0.0F))
		.uv(276, 241).cuboid(-61.0F, -72.0F, -7.5F, 31.0F, 2.0F, 15.0F, new Dilation(0.0F))
		.uv(248, 40).cuboid(-63.0F, -70.0F, -9.5F, 33.0F, 12.0F, 19.0F, new Dilation(0.0F))
		.uv(248, 147).cuboid(-30.0F, -70.0F, -12.0F, 27.0F, 12.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 56.0F, 46.0F, 1.5708F, -1.1345F, -1.5708F));

		ModelPartData body = t_rex.addChild("body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r10 = body.addChild("cube_r10", ModelPartBuilder.create().uv(220, 197).cuboid(31.0F, -41.0F, -5.0F, 38.0F, 24.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -31.0F, 78.0F, 1.5708F, -1.3963F, -1.5708F));

		ModelPartData cube_r11 = body.addChild("cube_r11", ModelPartBuilder.create().uv(448, 80).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -81.0F, 92.0F, 0.0F, -0.8727F, 0.0F));

		ModelPartData cube_r12 = body.addChild("cube_r12", ModelPartBuilder.create().uv(448, 80).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -81.0F, 92.0F, 0.0F, -2.2689F, 0.0F));

		ModelPartData cube_r13 = body.addChild("cube_r13", ModelPartBuilder.create().uv(144, 77).cuboid(9.0F, -48.0F, -12.0F, 38.0F, 30.0F, 24.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -33.0F, 59.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r14 = body.addChild("cube_r14", ModelPartBuilder.create().uv(116, 135).cuboid(-22.0F, -53.0F, -14.0F, 38.0F, 34.0F, 28.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -33.0F, 50.0F, -1.5708F, -1.3963F, 1.5708F));

		ModelPartData cube_r15 = body.addChild("cube_r15", ModelPartBuilder.create().uv(0, 287).cuboid(71.0F, -25.0F, -1.0F, 38.0F, 16.0F, 12.0F, new Dilation(0.0F)), ModelTransform.of(5.0F, -31.0F, 78.0F, 1.5708F, -1.2217F, -1.5708F));

		ModelPartData lungs = body.addChild("lungs", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -84.7374F, -11.2698F));

		ModelPartData cube_r16 = lungs.addChild("cube_r16", ModelPartBuilder.create().uv(448, 48).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -11.1356F, 41.3463F, -0.4754F, -0.7268F, 0.3295F));

		ModelPartData cube_r17 = lungs.addChild("cube_r17", ModelPartBuilder.create().uv(448, 48).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, -11.1356F, 41.3463F, -2.6662F, -0.7268F, 2.812F));

		ModelPartData cube_r18 = lungs.addChild("cube_r18", ModelPartBuilder.create().uv(0, 87).cuboid(-21.0F, -68.0F, -16.0F, 40.0F, 44.0F, 32.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 51.7374F, 15.2698F, -1.5708F, -1.2217F, 1.5708F));

		ModelPartData cube_r19 = lungs.addChild("cube_r19", ModelPartBuilder.create().uv(448, 80).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -26.1608F, -1.1274F, -0.3622F, -0.7519F, 0.2533F));

		ModelPartData cube_r20 = lungs.addChild("cube_r20", ModelPartBuilder.create().uv(448, 80).cuboid(-16.0F, -32.0F, 0.0F, 32.0F, 32.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-6.0F, -26.1608F, -1.1274F, -2.7794F, -0.7519F, 2.8883F));

		ModelPartData cube_r21 = lungs.addChild("cube_r21", ModelPartBuilder.create().uv(0, 0).cuboid(-49.0F, -71.0F, -19.0F, 34.0F, 49.0F, 38.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 51.7374F, 15.2698F, -1.5708F, -1.309F, 1.5708F));

		ModelPartData cube_r22 = lungs.addChild("cube_r22", ModelPartBuilder.create().uv(144, 0).cuboid(-59.0F, -75.0F, -16.0F, 20.0F, 45.0F, 32.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 51.7374F, 15.2698F, -1.5708F, -1.4399F, 1.5708F));

		ModelPartData left_arm = t_rex.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.pivot(16.0F, -80.0F, -42.0F));

		ModelPartData cube_r23 = left_arm.addChild("cube_r23", ModelPartBuilder.create().uv(156, 77).cuboid(-38.0F, -34.0F, -10.0F, 2.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 45.0F, 26.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r24 = left_arm.addChild("cube_r24", ModelPartBuilder.create().uv(80, 219).cuboid(-43.5F, -34.0F, -11.0F, 12.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 45.0F, 26.0F, -1.5708F, -1.4399F, 1.5708F));

		ModelPartData cube_r25 = left_arm.addChild("cube_r25", ModelPartBuilder.create().uv(0, 87).cuboid(-22.0F, -51.0F, -19.0F, 8.0F, 18.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-3.0F, 45.0F, 26.0F, 1.3963F, -1.3963F, -1.5708F));

		ModelPartData right_arm = t_rex.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(-16.0F, -80.0F, -42.0F));

		ModelPartData cube_r26 = right_arm.addChild("cube_r26", ModelPartBuilder.create().uv(24, 163).cuboid(-38.0F, -35.0F, 11.0F, 2.0F, 5.0F, 4.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 46.0F, 25.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r27 = right_arm.addChild("cube_r27", ModelPartBuilder.create().uv(220, 147).cuboid(-43.5F, -35.0F, 10.0F, 12.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 46.0F, 25.0F, -1.5708F, -1.4399F, 1.5708F));

		ModelPartData cube_r28 = right_arm.addChild("cube_r28", ModelPartBuilder.create().uv(112, 87).cuboid(-22.0F, -51.0F, 16.0F, 8.0F, 18.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 46.0F, 25.0F, 1.7453F, -1.3963F, -1.5708F));

		ModelPartData left_leg = t_rex.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(3.0F, -70.0F, 14.0F));

		ModelPartData left_leg_upper = left_leg.addChild("left_leg_upper", ModelPartBuilder.create(), ModelTransform.pivot(21.0F, -7.0F, 9.0F));

		ModelPartData cube_r29 = left_leg_upper.addChild("cube_r29", ModelPartBuilder.create().uv(120, 197).cuboid(-29.0F, -71.0F, -34.0F, 30.0F, 44.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(-19.0F, 64.0F, 3.0F, -1.7453F, -1.3963F, 1.5708F));

		ModelPartData left_leg_lower = left_leg.addChild("left_leg_lower", ModelPartBuilder.create(), ModelTransform.pivot(20.0F, 21.0F, 7.0F));

		ModelPartData cube_r30 = left_leg_lower.addChild("cube_r30", ModelPartBuilder.create().uv(172, 307).cuboid(-7.0F, -28.0F, -24.5F, 14.0F, 26.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(-18.0F, 49.0F, 3.0F, -1.5708F, -1.309F, 1.5708F));

		ModelPartData cube_r31 = left_leg_lower.addChild("cube_r31", ModelPartBuilder.create().uv(284, 273).cuboid(-3.0F, -42.0F, -26.0F, 20.0F, 36.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-18.0F, 36.0F, 5.0F, 1.5708F, -1.1345F, -1.5708F));

		ModelPartData left_foot = left_leg.addChild("left_foot", ModelPartBuilder.create(), ModelTransform.pivot(5.0F, 70.0F, 7.0F));

		ModelPartData cube_r32 = left_foot.addChild("cube_r32", ModelPartBuilder.create().uv(106, 0).cuboid(-24.0F, -8.0F, -24.0F, 24.0F, 8.0F, 10.0F, new Dilation(0.0F))
		.uv(138, 81).cuboid(-30.0F, -6.0F, -22.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, -1.1345F, 3.1416F));

		ModelPartData cube_r33 = left_foot.addChild("cube_r33", ModelPartBuilder.create().uv(268, 89).cuboid(-24.25F, -8.0F, -19.25F, 32.0F, 8.0F, 10.0F, new Dilation(0.0F))
		.uv(142, 18).cuboid(-30.25F, -6.0F, -17.25F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		ModelPartData cube_r34 = left_foot.addChild("cube_r34", ModelPartBuilder.create().uv(0, 315).cuboid(-12.0F, -8.0F, -12.0F, 24.0F, 8.0F, 10.0F, new Dilation(0.0F))
		.uv(0, 163).cuboid(-18.0F, -6.0F, -10.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1345F, 0.0F));

		ModelPartData right_leg = t_rex.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(-3.0F, -70.0F, 14.0F));

		ModelPartData right_leg_upper = right_leg.addChild("right_leg_upper", ModelPartBuilder.create(), ModelTransform.pivot(-21.0F, -7.0F, 9.0F));

		ModelPartData cube_r35 = right_leg_upper.addChild("cube_r35", ModelPartBuilder.create().uv(0, 219).cuboid(-30.0F, -71.0F, 14.0F, 30.0F, 44.0F, 20.0F, new Dilation(0.0F)), ModelTransform.of(19.0F, 64.0F, 4.0F, -1.3963F, -1.3963F, 1.5708F));

		ModelPartData right_leg_lower = right_leg.addChild("right_leg_lower", ModelPartBuilder.create(), ModelTransform.pivot(-20.0F, 21.0F, 7.0F));

		ModelPartData cube_r36 = right_leg_lower.addChild("cube_r36", ModelPartBuilder.create().uv(228, 307).cuboid(-7.0F, -28.0F, 10.5F, 14.0F, 26.0F, 14.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, 49.0F, 3.0F, -1.5708F, -1.309F, 1.5708F));

		ModelPartData cube_r37 = right_leg_lower.addChild("cube_r37", ModelPartBuilder.create().uv(100, 287).cuboid(-4.0F, -42.0F, 10.0F, 20.0F, 36.0F, 16.0F, new Dilation(0.0F)), ModelTransform.of(18.0F, 36.0F, 6.0F, 1.5708F, -1.1345F, -1.5708F));

		ModelPartData right_foot = right_leg.addChild("right_foot", ModelPartBuilder.create(), ModelTransform.pivot(-7.0F, 70.0F, 7.0F));

		ModelPartData cube_r38 = right_foot.addChild("cube_r38", ModelPartBuilder.create().uv(220, 131).cuboid(-19.0F, -6.0F, 2.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(316, 183).cuboid(-13.0F, -8.0F, 0.0F, 24.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -3.1416F, -1.1345F, 3.1416F));

		ModelPartData cube_r39 = right_foot.addChild("cube_r39", ModelPartBuilder.create().uv(244, 89).cuboid(-30.0F, -6.0F, 14.0F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(324, 107).cuboid(-24.0F, -8.0F, 12.0F, 24.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.1345F, 0.0F));

		ModelPartData cube_r40 = right_foot.addChild("cube_r40", ModelPartBuilder.create().uv(0, 175).cuboid(-30.25F, -6.0F, 10.25F, 6.0F, 6.0F, 6.0F, new Dilation(0.0F))
		.uv(172, 289).cuboid(-24.25F, -8.0F, 8.25F, 32.0F, 8.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
		return TexturedModelData.of(modelData, 512, 512);
	}
	@Override
	public void setAngles(TRexEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.setHeadAngles(netHeadYaw, headPitch);

		this.animateMovement(ModAnimations.TREX_WALK, limbSwing, limbSwingAmount, 1f, 1f);
		this.updateAnimation(entity.idleAnimationState, ModAnimations.TREX_IDLE, ageInTicks, 1f);
		this.updateAnimation(entity.attackAnimationState, ModAnimations.TREX_ATTACK, ageInTicks, 1f);
	}

	private void setHeadAngles(float headYaw, float headPitch) {
		headYaw = MathHelper.clamp(headYaw, -30.0F,30.0F);
		headPitch = MathHelper.clamp(headPitch, -30.0F,30.0F);

		this.head.yaw = headYaw * 0.017453292F;
		this.head.pitch = headPitch * 0.017453292F;
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		t_rex.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return t_rex;
	}
}